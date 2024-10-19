import { apiPost } from "../api/httpUtils";
const moduleName = "user";

export const registerUser = (params) => apiPost(`/${moduleName}/registerUser`, null, params);