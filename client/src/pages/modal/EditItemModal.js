import {observer} from "mobx-react-lite";
import {useState} from "react";
import {Button, Form, Modal} from "react-bootstrap";
import {editItem} from "../../http/ItemAPI";

const EditItemModal = observer(({show, onHide, item, itemId}) => {
    console.log(item)
    const [name, setName] = useState(item.name)
    const [description, setDescription] = useState(item.description)
    const [price, setPrice] = useState(item.price)


    const updateItem = () => {
        const formData = new FormData()
        formData.append('name', name)
        formData.append('price', `${price}`)
        formData.append('description', description)

        editItem(formData,itemId).then(data => onHide())
    }


    return (
        <Modal
            show={show}
            onHide={onHide}
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Редактировать  товар
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Control
                        value={name}
                        onChange={e => setName(e.target.value)}
                        className="mt-3"
                        placeholder="Введите название товара"
                    />
                    <Form.Control
                        value={price}
                        onChange={e => setPrice(Number(e.target.value))}
                        className="mt-3"
                        placeholder="Введите стоимость товара"
                        type="number"
                    />
                    <Form.Control
                        value={description}
                        onChange={e => setDescription(e.target.value)}
                        className="mt-3"
                        placeholder="Введите описание товара"
                    />
                    <hr/>

                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="outline-danger" onClick={onHide}>Закрыть</Button>
                <Button variant="outline-success" onClick={updateItem}>Сохранить</Button>
            </Modal.Footer>
        </Modal>
    );
});

export default EditItemModal;