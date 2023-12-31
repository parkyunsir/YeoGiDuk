import {combineReducers} from 'redux';
import {all} from 'redux-saga/effects';
import auth, {authSaga} from './auth';
import loading from './loading';
import myInfo, {myInfoSaga} from './myInfo';
import restaurant, {restaurantSaga} from './restaurant';
import search, {searchSaga} from './search';
import list, {listSaga} from './list';
import review, {reviewSaga} from './review';

const rootReducer = combineReducers({
  auth,
  loading,
  myInfo,
  restaurant,
  list,
  search,
  review,
});

export function* rootSaga() {
  yield all([authSaga(), myInfoSaga(), restaurantSaga(), listSaga(), searchSaga(), reviewSaga()]);
}

export default rootReducer;