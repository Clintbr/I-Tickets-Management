import api from './axios';

export const ticketApi = {
    // POST /api/tickets
    create: async (ticketData) => {
        const response = await api.post('/tickets', ticketData);
        return response.data;
    },
    // GET /api/tickets/my
    getMyTickets: async () => {
        const response = await api.get('/tickets/my');
        return response.data;
    },
    // GET /api/tickets/assigned (Für Support/Admins)
    getAssigned: async () => {
        const response = await api.get('/tickets/assigned');
        return response.data;
    },
    // GET /api/tickets/{id}
    getById: async (id) => {
        const response = await api.get(`/tickets/${id}`);
        return response.data;
    },
    // PATCH /api/tickets/{id}/status
    updateStatus: async (id, status) => {
        const response = await api.patch(`/tickets/${id}/status`, { status });
        return response.data;
    },
    // DELETE /api/tickets/{id}
    delete: async (id) => {
        const response = await api.delete(`/tickets/${id}`);
        return response.data;
    }
};