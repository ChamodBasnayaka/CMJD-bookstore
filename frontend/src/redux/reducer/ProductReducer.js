import { ActionTypes } from "../constants/Action-Types";


const productReducer = (state = {},{type,payload})=>{
    switch(type){
        case ActionTypes.SELECTED_PRODUCT:
            return {...state, ...payload};
        default:
            return state;
    }
 }
 export default productReducer;