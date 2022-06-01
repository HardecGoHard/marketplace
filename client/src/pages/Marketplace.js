import React, {useContext, useEffect, useState} from 'react';
import {observer} from "mobx-react-lite";
import {Context} from "../index";
import {fetchItems} from "../http/ItemAPI";
import {useHistory} from "react-router-dom";
import {APP_SERVER_HOST_IMAGE, ITEM_ROUTE} from "../util/Const";
import {Button, Image} from "react-bootstrap";
import monkey from "../assets/monkey.png"
import NewItemModal from "./modal/NewItemModal";

const Marketplace = observer(() => {
    const {item} = useContext(Context);
    const [itemVisible, setItemVisible] = useState(false)

    useEffect(() => {
        fetchItems().then(items => item.setItems(items))
    }, [])


    return (
        <div className="container my-2">
            <div className="h3 text-center">Все товары:</div>
            <Button variant={"outline-dark"} className="my-3" onClick={() => setItemVisible(true)} >Добавить новый товар</Button>
            <NewItemModal show={itemVisible} onHide={() =>{
                fetchItems().then(items => item.setItems(items))
                setItemVisible(false)
            }}/>
            <div className="divider"></div>
            <div className="row">
                {item.Items.map(i =>
                    <Item key={i.id} item={i}/>
                )}
            </div>
        </div>
    );
});


const Item = ({item}) => {
    const history = useHistory()


    return (
        <>
            <div className="d-flex col-md-4 mb-3">
                <div className="card p-2 box-shadow">
                    <Image className="card-img-top" src={APP_SERVER_HOST_IMAGE + item.avatar} height="200px" width="100px"/>
                    <div className="card-body">
                        <div className="card-title h5"
                             onClick={() => history.push(ITEM_ROUTE + '/' + item.id)}><a>{item.name}</a></div>
                        <p className="card-text"> {item.description}</p>
                        <div className="h5">Цена:
                            <label className="price mx-3 h6 text-warning">

                                {item.price} руб
                            </label>
                        </div>
                        <div onClick={() => history.push(ITEM_ROUTE + '/' + item.id)} className="btn btn-dark">Перейти к описанию</div>
                    </div>
                </div>
            </div>
        </>
    )
}
export default Marketplace;