import React, {useContext, useEffect, useState} from 'react';
import {observer} from "mobx-react-lite";
import {Button, Form, Image} from "react-bootstrap";
import {Context} from "../index";
import {editProfile, getUser, uploadAvatar} from "../http/UserApi";
import {APP_SERVER_HOST, APP_SERVER_HOST_IMAGE} from "../util/Const";


const Profile = observer(() => {
    const {user} = useContext(Context)
    const [authUser, setAuthUser] = useState({})

    const [name, setName] = useState('')
    const [email, setEmail] = useState('')
    const [surname, setSurname] = useState('')
    const [file, setFile] = useState(null)

    useEffect(() => {
        getUser(user.getUser.userId).then(u => setAuthUser(u))
    }, [])


    const selectFile = e => {
        const formData = new FormData();
        formData.append('avatar', e.target.files[0]);
        uploadAvatar(user.getUser.userId, formData).then(r => setFile(null))
        getUser(user.getUser.userId).then(u => setAuthUser(u))
    }

    const updateProfile = () => {
        const formData = new FormData()
        formData.append('name', name)
        formData.append('email', email)
        formData.append('surname', surname)

        editProfile(formData).then(() => getUser(user.getUser.userId).then(u => setAuthUser(u)))

    }


    return (

        <div className="container my-2 border-2 ">
            <div className="h3 text-center">Профиль пользователя</div>

            <Form >
                <div className="d-flex flex-row mt-4 flex-wrap ">
                    <div className="d-flex flex-column flex-shrink-0 col-12 col-sm-4 col-xs-4">
                        <div className="d-flex justify-content-center flex-column">
                            <div className="profile__avatar-container">
                                <Image src={APP_SERVER_HOST_IMAGE + authUser.avatar}
                                     className="border rounded-circle" width="300px" height="300px"/>
                            </div>
                            <div className="px-3">
                                <Form.Control
                                    className="mt-3"
                                    type="file"
                                    onChange={selectFile}
                                />
                            </div>
                        </div>
                    </div>

                    <div className="d-flex flex-shrink-0 flex-column col-12 col-sm-8 col-xs-4 border border p-2">
                        <div>
                            <div className="h6">Логин: {authUser.username}</div>
                            <div className="h6">Имя: {authUser.name}</div>
                            <div className="h6">Фамилия: {authUser.surname}</div>
                            <div className="h6">email: {authUser.email}</div>

                        </div>
                        <div className="h5 text-center">Редактировать информацию о пользователе</div>

                        <div className="form-group">
                            <label htmlFor="name" className="font-weight-bolder my-2">Имя </label>
                            <input  value={name}
                                    onChange={e => setName(e.target.value)}
                                    type="text" id="name" name="name" placeholder="Имя"
                                   className="form-control form-control-sm"/>
                        </div>
                        <div  className="form-group my-2">
                            <label  className="font-weight-bolder">Фамилия </label>
                            <input
                                value={surname}
                                onChange={e => setSurname(e.target.value)}
                                type="text" id="surname" name="surname"
                                   placeholder="Фамилия" className="form-control form-control-sm"/>
                        </div>

                        <div className="form-group my-2">
                            <label  className="font-weight-bolder">Email</label>
                            <input
                                value={email}
                                onChange={e => setEmail(e.target.value)}

                                type="email" id="email" name="email"
                                   placeholder="Email" className="form-control form-control-sm"/>
                        </div>
                        <Button variant="outline-success" className="my-2" onClick={updateProfile}>Сохранить</Button>

                    </div>

                </div>
            </Form>
        </div>
);
});

export default Profile;