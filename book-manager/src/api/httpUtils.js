import axios from 'axios';

const errorHandler = () => {
  // TODO 未配置错误页面,暂时直接return
  // switch  (status) {
  // case 401:
  //     _this.$_error(401);
  //     break;
  // case 403:
  //     _this.$_error(403, {
  //         msg: '该账号已在其他地方登录，您已被迫下线，如非本人操作请重新登录并及时修改密码！'
  //     });
  //     break;
  // case 404:
  //     _this.$_error(404);
  //     break;
  // case 504:
  //     _this.$_error(504);
  //     break;
  // default:
  //     _this.$_error(500, {
  //         msg
  //     });
  //     break;
  // }
};
function requestRecall(method, url, urlParams, bodyParams, headers, responseType = 'json', timeout = 1000 * 600) {
  // 设置请求头参数
  const axiosHeaders = {
    'Cache-Control': 'no-cache',
    'Pragma': 'no-cache',
    'Expires': '0'
  };
  if (headers) {
    Object.assign(axiosHeaders, headers);
  }
  return new Promise((resolve, reject) => {
    axios({
      baseURL: process.env.NODE_ENV === 'production' ? process.env.VUE_APP_API_URL : '',
      url,
      method,
      withCredentials: true,
      params: urlParams,
      data: bodyParams,
      headers: axiosHeaders,
      responseType,
      timeout,
    })
      .then((response) => {
        if (responseType === 'blob') {
          resolve(response.data);
          return;
        }
        
        const { code, msg } = response.data;
        if (code != 200) {
          errorHandler(code, msg);
          reject(response.data);
        } else {
          resolve(response.data);
        }
      })
      .catch((err) => {
        const status = err?.response?.status;
        const msg = err?.response?.data.message || err?.response?.data.msg || '服务暂时不可用请稍后再试';
        console.error(msg);
        errorHandler(status, msg);
        reject(err.response.data);
      });
  });
}
function _request(method, url, urlParams, bodyParams, headers, responseType = 'json', timeout = 1000 * 600) {
  return requestRecall(method, url, urlParams, bodyParams, headers, responseType, timeout, 0);
}
/**
 * GET
 * @param {String} requestURL 请求路径
 * @param {Object} urlParams URL 路径中的参数
 * @param {Object} headers 请求头
 * @param {String} responseType 响应类型
 * @param {String} timeout 超时时间
 * @return {Promise} promise对象
 */
export function apiGet(requestURL, urlParams, headers, responseType, timeout) {
  return _request('get', requestURL, urlParams, {}, headers, responseType, timeout);
}

/**
 * POST
 * @param {String} requestURL 请求路径
 * @param {Object} urlParams URL参数
 * @param {Object} bodyParams body参数
 * @param {Object} headers 请求头
 * @param {String} responseType 响应类型
 * @param {String} timeout 超时时间
 * @returns {Promise} promise对象
 */
export function apiPost(requestURL, urlParams, bodyParams, headers, responseType, timeout) {  
  return _request('post', requestURL, urlParams, bodyParams, headers, responseType, timeout);
}

/**
 * PUT
 * @param {String} requestURL 请求路径
 * @param {Object} urlParams URL参数
 * @param {Object} bodyParams body参数
 * @returns {Promise} promise对象
 */
export function apiPut(requestURL, urlParams, bodyParams) {
  return _request('put', requestURL, urlParams, bodyParams);
}

/**
 * DELETE
 * @param {String} requestURL 请求路径
 * @param {Object} urlParams URL参数
 * @param {Object} bodyParams body参数
 * @param {Object} headers 请求头
 * @returns {Promise} promise对象
 */
export function apiDelete(requestURL, urlParams, bodyParams, headers) {
  return _request('delete', requestURL, urlParams, bodyParams, headers);
}

/**
 * PATCH
 * @param {String} requestURL 请求路径
 * @param {Object} urlParams URL参数
 * @param {Object} bodyParams body参数
 * @returns {Promise} promise对象
 */
export function apiPatch(requestURL, urlParams, bodyParams) {
  return _request('patch', requestURL, urlParams, bodyParams);
}

/**
 * 基础请求函数
 * @param {String} method 请求方式
 * @param {String} url 接口地址
 * @param {Object} urlParams URL参数
 * @param {Object} bodyParams body参数
 * @param {Object} headers 请求头
 * @param {String} responseType 响应类型
 * @returns
 */

export { _request };
