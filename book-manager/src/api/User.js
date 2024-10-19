import { apiPost } from "../api/httpUtils";
const moduleName = "user";

export const registerUser = (params) => apiPost(`/${moduleName}/registerUser`, null, params);
export const registerManager = (params) => apiPost(`/${moduleName}/registerManager`, null, params);
export const getCode = (params) => apiPost(`/${moduleName}/code`, null, params, null, 'blob');
export const userLogin = (params) => apiPost(`/${moduleName}/login`, null, params);