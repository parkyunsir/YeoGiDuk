import React from 'react';
import HeaderContainer from '../containers/common/HeaderContainer';
import RecommendContainer from '../containers/RecommendContainer';
import SubHeader from '../components/common/SubHeader';

const RecommendPage = () => {
  return (
    <>
      <HeaderContainer />
      <SubHeader />
      <RecommendContainer/>
    </>
  );
};

export default RecommendPage;