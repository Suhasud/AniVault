import Link from "next/link";

export default function Home() {
    return (
        <main className="flex min-h-screen items-center justify-center bg-gray-950 px-6">
            <div className="w-full max-w-4xl text-center">
                <div className="mb-6 text-6xl">
                    🎌
                </div>

                <h1 className="text-5xl font-bold tracking-tight text-white sm:text-6xl">
                    AniVault
                </h1>

                <p className="mx-auto mt-6 max-w-2xl text-lg leading-8 text-gray-400">
                    Your personal anime watchlist.
                    Track what you&apos;re watching, manage your progress,
                    and keep your entire anime collection organized.
                </p>

                <div className="mt-10 flex flex-col items-center justify-center gap-4 sm:flex-row">
                    <Link
                        href="/login"
                        className="rounded-lg bg-purple-600 px-6 py-3 font-semibold text-white transition hover:bg-purple-700"
                    >
                        Sign In
                    </Link>

                    <Link
                        href="/register"
                        className="rounded-lg border border-gray-700 px-6 py-3 font-semibold text-gray-300 transition hover:border-purple-500 hover:bg-gray-900 hover:text-white"
                    >
                        Create Account
                    </Link>
                </div>

                <div className="mt-16 grid grid-cols-1 gap-6 text-left sm:grid-cols-3">
                    <div className="rounded-xl border border-gray-800 bg-gray-900 p-6">
                        <h2 className="text-lg font-semibold text-white">
                            📚 Manage
                        </h2>
                        <p className="mt-2 text-sm text-gray-400">
                            Keep your anime collection organized in one place.
                        </p>
                    </div>

                    <div className="rounded-xl border border-gray-800 bg-gray-900 p-6">
                        <h2 className="text-lg font-semibold text-white">
                            📊 Track Progress
                        </h2>
                        <p className="mt-2 text-sm text-gray-400">
                            Track watched episodes and your current watch status.
                        </p>
                    </div>

                    <div className="rounded-xl border border-gray-800 bg-gray-900 p-6">
                        <h2 className="text-lg font-semibold text-white">
                            🔐 Secure
                        </h2>
                        <p className="mt-2 text-sm text-gray-400">
                            Your account is protected with JWT authentication.
                        </p>
                    </div>
                </div>
            </div>
        </main>
    );
}
