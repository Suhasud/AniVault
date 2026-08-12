import api from "@/lib/api";
import type {
    LoginRequest,
    LoginResponse,
    RegisterRequest,
} from "@/types/auth";

export const authService = {
    async login(credentials: LoginRequest): Promise<LoginResponse> {
        const response = await api.post<LoginResponse>(
            "/auth/login",
            credentials,
        );

        return response.data;
    },

    async register(
        credentials: RegisterRequest,
    ): Promise<string> {
        const response = await api.post<string>(
            "/auth/register",
            credentials,
        );

        return response.data;
    },
};