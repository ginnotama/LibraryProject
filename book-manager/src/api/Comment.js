import { apiPost } from "../api/httpUtils";
const moduleName = "comment";

export const addComment = (params) => apiPost(`/${moduleName}/addComment`, null, params);
export const getComments = (params) => apiPost(`/${moduleName}/getComments`, null, params);
