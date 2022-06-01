import {$authHost, $baseHost} from "./index";
import jwt_decode from "jwt-decode";

export const registration = async (email, username, password) => {
    const {data} = await $baseHost.post('auth/registration', {email, username, password})
    localStorage.setItem("accessToken", data.accessToken)
    return jwt_decode(data.accessToken)
}


export const check = async () => {
    const {data} = await $authHost.post('auth/check')
    localStorage.setItem("accessToken", data.accessToken)
    return jwt_decode(data.accessToken)
}

export const loginMethod = async (username, password) => {
    const {data} = await $baseHost.post('auth/login', {username, password})
    localStorage.setItem("accessToken", data.accessToken)
    return jwt_decode(data.accessToken)
}


export const getUser = async (userId) => {
    const {data} = await $authHost.get('user/' + userId)
    return data
}

export const uploadAvatar = async (userId, avatar) => {
    const config = {
        headers: {
            'content-type': 'multipart/form-data',
        },
    };

    await $authHost.post('user/' + userId + "/avatar", avatar, config)

}

export const editProfile = async (formData) => {
    const {data} = await $authHost.put('user/profile', formData)
    return data
}

