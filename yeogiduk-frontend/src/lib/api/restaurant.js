import client from './client';

export const restaurantReviews = rstId =>
  client.get(`/restaurant/${rstId}/reviews`);

export const restaurantDetail = rstId =>
  client.get(`/restaurant/detail/${rstId}`);

export const rtype = rstId =>
  client.get(`/rtype/${rstId}`);

export const search = word =>
  client.get(`/restaurant/search/${word}`);

export const likeNum = rstId =>
  client.get(`/restaurant/likes/list/${rstId}`);

export const menu = rstId =>
  client.get(`/menu/${rstId}`);

export const getImage = rstId =>
  client.get(`/image/show/one/${rstId}`);

export const addLike = ({id, email, rstId}) =>
  client.post(`/student/likes`, {id, email, rstId});

export const checkLike = ({email, rstId}) =>
  client.get(`/student/likes/restaurant/${email}?rst_id=${rstId}`);