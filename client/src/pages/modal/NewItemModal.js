import {observer} from "mobx-react-lite";
import {useContext, useState} from "react";
import {Button, Col, Dropdown, Form, Modal, Row} from "react-bootstrap";
import {Context} from "../../index";
import {createItem} from "../../http/ItemAPI";

const NewItemModal = observer(({show, onHide}) => {
    const {item} = useContext(Context)
    const [name, setName] = useState('')
    const [description, setDescription] = useState('')
    const [price, setPrice] = useState(0)
    const [file, setFile] = useState(null)


    const selectFile = e => {
        setFile(e.target.files[0])
    }

    const addItem = () => {
        const formData = new FormData()
        formData.append('name', name)
        formData.append('price', `${price}`)
        formData.append('description', description)
        formData.append('avatar', file)

        createItem(formData).then(data => {
            onHide()
            setName('')
            setDescription('')
            setPrice(0)
            setFile(null)
            }
        )
    }

    return (
        <Modal
            show={show}
            onHide={onHide}
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Добавить  товар
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
                    <Form.Control
                        className="mt-3"
                        type="file"
                        onChange={selectFile}
                    />
                    <hr/>

                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="outline-danger" onClick={onHide}>Закрыть</Button>
                <Button variant="outline-success" onClick={addItem}>Добавить</Button>
            </Modal.Footer>
        </Modal>
    );
});

export default NewItemModal;