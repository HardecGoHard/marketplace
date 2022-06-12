import React, {useContext} from 'react';
import {Redirect, Route, Switch} from 'react-router-dom'
import {authRoutes, publicRoutes} from "../routes";
import {Context} from "../index";
import {observer} from "mobx-react-lite";
import {MAIN_ROUTE} from "../util/Const";

const AppRouter = observer(() => {
    const {user} = useContext(Context)

    return (
        <Switch>
            {
                user.isAuth && authRoutes.map(({path, Component}) =>
                    <Route key={path} path={path} component={Component} exact/>
                )}

            {
                !user.isAuth && publicRoutes.map(({path, Component}) =>
                    <Route key={path} path={path} component={Component} exact/>
                )}

            <Redirect to={MAIN_ROUTE}/>
        </Switch>
    );
});

export default AppRouter;