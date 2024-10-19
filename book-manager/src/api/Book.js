import { apiPost } from "../api/httpUtils";
const moduleName = "book";

export const getBooks = (params) => apiPost(`/${moduleName}/books`, null, params);
export const addBook = (params) => apiPost(`/${moduleName}/addBook`, null, params);
export const searchBookByType = (params) => apiPost(`/${moduleName}/types`, null, params);
export const updateBook = (params) => apiPost(`/${moduleName}/updateBook`, null, params);