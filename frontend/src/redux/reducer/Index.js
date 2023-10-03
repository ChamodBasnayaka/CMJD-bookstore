import { combineReducers } from "redux";
import productReducer from "./ProductReducer";
import CartReducer from "./cartReducers";
const reducers = combineReducers({
    product: productReducer,
    cart: CartReducer
})

export default reducers;