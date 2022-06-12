import {$authHost} from "./index";


export const fetchItems = async () => {
    const {data} = await $authHost.get('item')
    return data._embedded.itemDtoList
}

export const createItem = async (item) => {
    const {data} = await $authHost.post('item', item)
    return data
}

export const editItem = async (item, id) => {
    const {data} = await $authHost.put('item/' + id, item)
    return data
}

export const uploadAvatarItem = async (avatar, id) => {
    const config = {
        headers: {
            'content-type': 'multipart/form-data',
        },
    };
    await $authHost.post('item/' + id + "/avatar", avatar, config)
}

export const deleteItem = async (id) => {
    const {data} = await $authHost.delete('item/' + id)
    return data
}

export const fetchOneItem = async (id) => {
    const {data} = await $authHost.get('item/' + id)
    return data
}
