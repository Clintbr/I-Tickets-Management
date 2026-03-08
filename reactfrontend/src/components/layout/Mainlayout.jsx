import { Outlet } from 'react-router-dom';
import Sidebar from './Sidebar';
import Navbar from './Navbar';
import Footer from "./Footer.jsx"; // Pfad ggf. anpassen

const MainLayout = () => {
    return (
        <div className="flex h-screen bg-gray-50 overflow-hidden">
            <Sidebar />

            <div className="flex-1 flex flex-col min-w-0">
                <Navbar />

                <main className="flex-1 overflow-y-auto flex flex-col">
                    <div className="flex-1 p-4 md:p-8">
                        <div className="max-w-7xl mx-auto">
                            <Outlet />
                        </div>
                    </div>

                    <Footer />
                </main>
            </div>
        </div>
    );
};

export default MainLayout;