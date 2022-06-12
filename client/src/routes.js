import {ITEM_ROUTE, LOGIN_ROUTE, MAIN_ROUTE, NEW_ITEM_ROUTE, PROFILE_ROUTE, REG_ROUTE} from "./util/Const";
import Marketplace from "./pages/Marketplace";
import Profile from "./pages/Profile";
import Auth from "./pages/Auth";
import ItemInfo from "./pages/ItemInfo";

export const authRoutes = [
    {
        path: MAIN_ROUTE,
        Component: Marketplace
    },
    {
        path: PROFILE_ROUTE,
        Component: Profile
    },
    {
        path: ITEM_ROUTE,
        Component: Marketplace
    },
    {
        path: ITEM_ROUTE + '/:id',
        Component: ItemInfo
    }
]

export const publicRoutes = [
    {
        path: LOGIN_ROUTE,
        Component: Auth
    },
    {
        path: REG_ROUTE,
        Component: Auth
    },
]
