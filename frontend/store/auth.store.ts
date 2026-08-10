import { create } from "zustand";

interface AuthState {
    token: string | null;
    setToken: (token: string) => void;
    clearToken: () => void;
}

export const useAuthStore = create<AuthState>((set) => ({
    token: null,

    setToken: (token) => {
        localStorage.setItem("anivault_token", token);
        set({ token });
    },

    clearToken: () => {
        localStorage.removeItem("anivault_token");
        set({ token: null });
    },
}));