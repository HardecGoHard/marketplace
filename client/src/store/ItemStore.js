import {makeAutoObservable} from "mobx";

export default class ItemStore {
    constructor() {
        this._items = []
        this._comments = []

        makeAutoObservable(this)
    }

    setItems(items) {
        this._items = items
    }

    setComments(comments) {
        this._comments = comments
    }

    setPage(page) {
        this._page = page
    }

    setTotalCount(count) {
        this._totalCount = count
    }

    get Items() {
        return this._items
    }

    get Comments() {
        return this._comments
    }

    get Page() {
        return this._page
    }

    get TotalCount() {
        return this._totalCount
    }
}