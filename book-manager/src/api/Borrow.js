import { apiPost } from "../api/httpUtils";
const moduleName = "borrow";

export const getBorrows = (params) => apiPost(`/${moduleName}/getBorrows`, null, params);
export const returnBook = (params) => apiPost(`/${moduleName}/returnBook`, null, params);