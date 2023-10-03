

import { ActionTypes } from "../constants/Action-Types"

const initialState = {
    items: [],
    total: 0,
    books:[]
};

const CartReducer = (state = initialState, { type, payload }) => {
    switch (type) {
        case ActionTypes.ADD_TO_CART:
            const existingItem = state.items.find(item=>item.id===payload.id);
            if(existingItem){
                existingItem.amount = existingItem.amount+payload.amount;
                state.total += payload.price*payload.amount;
                return state;
                
            }else{
                return {
                    ...state,
                    items: [...state.items, payload],
                    total: state.total+payload.price*payload.amount,
                    books:[...state.books,payload.product]
                };
            }
            
        default:
            return state;
    }
}
export default CartReducer;