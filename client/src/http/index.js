import axios from "axios";
import {APP_SERVER_HOST} from "../util/Const";

const $baseHost = axios.create({
    baseURL: APP_SERVER_HOST
})

const $authHost = axios.create({
    baseURL: APP_SERVER_HOST
})

const authInterceptor = config => {
    const token = `${localStorage.getItem('accessToken')}`
    config.headers = {
        'access-token': token ? `${token}` : ''
    }
    return config
}

$authHost.interceptors.request.use(authInterceptor)

export {
    $baseHost,
    $authHost
}