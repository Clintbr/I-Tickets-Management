import { useContext } from 'react';
import { AuthContext } from '../../context/AuthContext';

const Navbar = () => {
    const { user } = useContext(AuthContext);

    return (
        <header className="h-16 bg-white border-b border-slate-200 flex items-center justify-between px-8 shadow-sm">
            <div className="text-sm text-slate-500">
                Übersicht / <span className="text-slate-900 font-medium">Dashboard</span>
            </div>

            <div className="flex items-center space-x-4">
                {user ? (
                    <div className="flex items-center space-x-3">
                        <div className="text-right">
                            <p className="text-sm font-semibold text-slate-900">{user.username}</p>
                            <p className="text-xs text-blue-600 font-medium uppercase">{user.role}</p>
                        </div>
                        <div className="w-10 h-10 bg-blue-100 text-blue-600 flex items-center justify-center rounded-full font-bold">
                            {user.username.charAt(0).toUpperCase()}
                        </div>
                    </div>
                ) : (
                    <span className="text-sm text-slate-400">Nicht angemeldet</span>
                )}
            </div>
        </header>
    );
};

export default Navbar;