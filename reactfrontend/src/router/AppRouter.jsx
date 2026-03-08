import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { useContext } from 'react';
import { AuthContext } from '../context/AuthContext';
import MainLayout from '../components/layout/MainLayout';

import Home from '../pages/Home';
import Login from '../pages/Login';
import Register from '../pages/Register';
import Tickets from '../pages/Tickets';
import TicketDetails from '../pages/TicketDetails';
import SupportDashboard from '../pages/SupportDashboard';
import AdminDashboard from '../pages/AdminDashboard';


const AppRouter = () => {
    const { user, loading } = useContext(AuthContext);

    if (loading) return <div className="flex h-screen items-center justify-center font-bold text-blue-600">Lade App-Daten...</div>;

    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<MainLayout />}>
                    <Route index element={<Home />} />

                    <Route path="login" element={!user ? <Login /> : <Navigate to="/" />} />
                    <Route path="register" element={!user ? <Register /> : <Navigate to="/" />} />

                    <Route path="tickets" element={user ? <Tickets /> : <Navigate to="/login" />} />
                    <Route path="tickets/:id" element={user ? <TicketDetails /> : <Navigate to="/login" />} />

                    <Route
                        path="support"
                        element={user?.role === 'SUPPORT' || user?.role === 'ADMIN' ? <SupportDashboard /> : <Navigate to="/" />}
                    />

                    <Route
                        path="admin"
                        element={user?.role === 'ADMIN' ? <AdminDashboard /> : <Navigate to="/" />}
                    />

                    <Route path="*" element={<Navigate to="/" />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
};

export default AppRouter;