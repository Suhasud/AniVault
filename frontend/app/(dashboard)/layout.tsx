import Navbar from "@/components/layout/Navbar";
import Sidebar from "@/components/layout/Sidebar";

export default function DashboardLayout({
                                            children,
                                        }: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <div className="min-h-screen bg-gray-950 text-white">
            <Navbar />

            <div className="flex">
                <Sidebar />

                <main className="min-w-0 flex-1">
                    {children}
                </main>
            </div>
        </div>
    );
}