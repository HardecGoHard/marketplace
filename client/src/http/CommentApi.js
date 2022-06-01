import {$authHost} from "./index";

export const addComment = async (text, itemId) => {
    const {data} = await $authHost.post('item/' + itemId + '/comment', {text})
    return data
}

export const fetchComments = async (itemId) => {
    const {data} = await $authHost.get('item/' + itemId + '/comment')
    return data._embedded.commentDtoList
}

export const delComment = async (itemId, commentId) => {
    const {data} = await $authHost.delete('item/' + itemId + '/comment/' + commentId)
    return data
}