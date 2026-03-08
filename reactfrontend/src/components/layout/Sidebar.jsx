import { useContext } from 'react';
import { NavLink } from 'react-router-dom';
import { AuthContext } from '../../context/AuthContext';

const Sidebar = () => {
    const { user, logout } = useContext(AuthContext);

    const navItems = [
        { name: 'Startseite', path: '/', roles: ['ANY'] },
        { name: 'Meine Tickets', path: '/tickets', roles: ['USER', 'SUPPORT', 'ADMIN'] },
        { name: 'Admin Bereich', path: '/admin', roles: ['ADMIN'] },
        { name: 'Support Board', path: '/support', roles: ['SUPPORT', 'ADMIN'] },
    ];

    return (
        <aside className="w-64 bg-slate-900 text-white flex flex-col transition-all duration-300">
            <div className="p-6 text-2xl font-bold border-b border-slate-800">
                Ticket<span className="text-blue-500">System</span>
            </div>

            <nav className="flex-1 mt-6 px-4 space-y-2">
                {navItems.map((item) => (
                    (item.roles.includes('ANY') || item.roles.includes(user?.role)) && (
                        <NavLink
                            key={item.path}
                            to={item.path}
                            className={({ isActive }) =>
                                `block px-4 py-2.5 rounded-lg transition-colors ${
                                    isActive
                                        ? 'bg-blue-600 text-white'
                                        : 'text-slate-400 hover:bg-slate-800 hover:text-white'
                                }`
                            }
                        >
                            {item.name}
                        </NavLink>
                    )
                ))}
            </nav>

            <div className="p-4 border-t border-slate-800">
                {!user ? (
                    <div className="space-y-2">
                        <NavLink to="/login" className="block w-full text-center py-2 bg-blue-600 hover:bg-blue-700 rounded-lg transition">
                            Anmelden
                        </NavLink>
                        <NavLink to="/register" className="block w-full text-center py-2 border border-slate-700 hover:bg-slate-800 rounded-lg transition">
                            Registrieren
                        </NavLink>
                    </div>
                ) : (
                    <button
                        onClick={logout}
                        className="w-full py-2 text-slate-400 hover:text-red-400 hover:bg-red-400/10 rounded-lg transition-all"
                    >
                        Abmelden
                    </button>
                )}
            </div>
        </aside>
    );
};

export default Sidebar;