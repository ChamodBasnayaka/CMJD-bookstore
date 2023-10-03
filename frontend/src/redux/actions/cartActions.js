import { ActionTypes } from "../constants/Action-Types"

const addToCart = (book)=>{
    return{
        type:ActionTypes.ADD_TO_CART,
        payload: book,
    }
}

export default addToCart;