import React, {useContext, useEffect, useState} from 'react';
import {observer} from "mobx-react-lite";
import {Button, Form, Image} from "react-bootstrap";
import {useHistory, useParams} from "react-router-dom";
import {deleteItem, fetchOneItem, uploadAvatarItem} from "../http/ItemAPI";
import EditItemModal from "./modal/EditItemModal";
import {addComment, fetchComments} from "../http/CommentApi";
import {faTrashCan} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {APP_SERVER_HOST_IMAGE, ITEM_ROUTE} from "../util/Const";
import {Context} from "../index";

const ItemInfo = observer(() => {
    const history = useHistory()
    const {user} = useContext(Context)

    const [item, setItem] = useState({})
    const [itemVisible, setItemVisible] = useState(false)
    const [file, setFile] = useState(null)

    const {id} = useParams()

    useEffect(() => {
        fetchOneItem(id).then(data => setItem(data))
    }, [])

    const selectFile = async e => {

        const formData = new FormData()
        formData.append('avatar', e.target.files[0])

        uploadAvatarItem(formData, id).then(data => [])
    }

    const deleteClick = async () => {
        await deleteItem(id)
        history.push(ITEM_ROUTE)
    }

    return (
        <div className="container my-5">
            <div className="card p-4">
                <div className="row">
                    <div className="col-sm-5 border-right">
                        <div className="gallery-wrap mt-4">
                            <div>
                                <Image width="500" height="400" src={APP_SERVER_HOST_IMAGE + item.avatar}/>
                            </div>
                            <Form.Control
                                className="mt-3"
                                type="file"
                                onChange={selectFile}
                            />
                            <Button className="mt-2" onClick={() => fetchOneItem(id).then(data => setItem(data))
                            } variant={"outline-success"}>Обновить</Button>
                        </div>
                    </div>
                    <div className="col-sm-7">
                        <div className="card-body p-5">
                            <div className="h3">Название:
                                <label className="title h4 mx-3">{item.name}</label>
                            </div>

                            <div className="h3">Цена:
                                <label className="price mx-3 h4 text-warning">

                                    {item.price} руб
                                </label>
                            </div>
                            <div className="item-property">
                                <p className="h4">Описание:</p>
                                <dd><p> {item.description}</p></dd>
                            </div>
                        </div>
                    </div>
                </div>

                <hr/>

                {user.getUser.userId == item.ownerId ?
                    <>
                        <Button className="fa-pull-right my-3" variant={"outline-primary"}
                                onClick={() => setItemVisible(true)}>Редактировать</Button>

                        <EditItemModal show={itemVisible} onHide={() => {
                            setItemVisible(false)
                            fetchOneItem(id).then(data => setItem(data))
                        }}
                                       item={item} itemId={id}/>


                        <Button className="fa-pull-right my-3" variant={"outline-danger"}
                                onClick={deleteClick}>Удалить</Button>
                    </>
                    :
                    <>
                    </>
                }

                <Comments itemId={id}/>

            </div>

        </div>

    );
});

export default ItemInfo;


const Comments = observer(({itemId}) => {
    const [commentText, setCommentText] = useState('')
    const [comment, setComment] = useState([])
    const [file, setFile] = useState(null)

    useEffect(() => {
        fetchComments(itemId).then(c => setComment(c))
    }, [])

    const selectFile = e => {
        setFile(e.target.files[0])
    }

    const click = async () => {
        try {
            let data = {}
            data = await addComment(commentText, itemId);
            fetchComments(itemId).then(c => {
                    setComment(c)
                    setCommentText('')
                }
            )

        } catch (e) {
            alert(e.response.data.message)
        }

    }

    return (
        <>
            <div className="h3 my-3"> Комментарии:</div>

            {comment.map(com =>
                <Comment coment={com}/>
            )}

            <div className="d-flex flex-column flex-grow-1 mb-3 mt-3 border-top pt-3">
            <textarea class="form-control" rows="3" onChange={e => setCommentText(e.target.value)}>

            </textarea>
                <div className="my-3">
                    <button className="btn btn-sm btn-success float-right" onClick={click}> Комментировать</button>
                </div>
            </div>
        </>
    );
});


const Comment = ({coment}) => {

    return (
        <>
            <div className="d-flex flex-row">
                <div className="pr-3 d-none d-sm-block"><img height="40px"
                                                             width="40px"
                                                             src={APP_SERVER_HOST_IMAGE + coment.author.avatar}
                                                             className="rounded-circle"/>
                </div>
                <div className="card mb-3 w-100">
                    <div className="card-header py-2 d-flex align-items-center">
                        <div className="flex-grow-1 text-truncate">

                            <span
                                className="font-weight-bolder">{coment.author.fullName}</span>

                            <span

                                className="mr-1 text-muted text-decoration-none"> @{coment.author.username} </span>
                            <span className="f6 text-muted">{coment.author.date}</span>
                            <FontAwesomeIcon icon={faTrashCan} className="btn mx-1 fa-pull-right"/>

                        </div>

                    </div>
                    <div className="card-body break-word p-0">
                        <div className="w-100">
                            <div className="markdown-body px-4 py-3 break-word"><p>{coment.text}</p></div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

