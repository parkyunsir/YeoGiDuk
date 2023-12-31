import React, {useState, useEffect} from 'react';
import styled from 'styled-components';
import { getReviewImages } from '../../lib/api/review';

const ReviewBlock = styled.div`
  margin-bottom: 2rem;
  margin-left:-1rem;
`;

const Horizon = styled.div`
  display:flex;
  float:left;
  margin-bottom:0.5rem;
`;

const Name = styled.div`
  font-weight:bold;
  font-size:22px;
`;

const Star = styled.div`
  color: #f87f9c;

`;

const BlankStar = styled.div`
  color: #000000;
`;

const Bold = styled.div`
  font-weight:bold;
  color: #000000;
`;

const Content = styled.div`
  clear: both;
`;

const ReviewDate = styled.div`
  float:right;
  font-size:12px;
  color: #888888;
`;

const TextGray = styled.div`
  hr {
    solid #888888; /* 또는 다른 회색 색상으로 변경 가능 */
  }
`;

const Image = styled.img`
  width: 150px;
  height: 150px;
  margin-top: 0.3rem;
  margin-bottom: 0.1rem;
  margin-right: 5px;
`;

const Review = ({review}) => {
  const date = new Date(review.date).toLocaleDateString();
  const [stars, setStars] = useState(`★★★★★`);
  useEffect(() => {
    if(review.star === 5) {
      setStars(`★★★★★`);
    } else if (review.star === 4) {
      setStars(`★★★★`);
    } else if (review.star === 3) {
      setStars(`★★★`);
    } else if (review.star === 2) {
      setStars(`★★`);
    } else if (review.star === 1) {
      setStars(`★`);
    }
  }, [review.star, review]);

  const [blankstars, setblankStars] = useState(`★★★★★`);
  useEffect(() => {
    if(review.star === 5) {
      setblankStars(``);
    } else if (review.star === 4) {
      setblankStars(`☆`);
    } else if (review.star === 3) {
      setblankStars(`☆☆`);
    } else if (review.star === 2) {
      setblankStars(`☆☆☆`);
    } else if (review.star === 1) {
      setblankStars(`☆☆☆☆`);
    }
  }, [review.star, review]);

  const [images, setImages] = useState([]);
  useEffect(() => {
    const fetchImage = async () => {
      try {
        const response = await fetch(`/image/show/${review.viewId}`);
        //const response = await getReviewImages(review.viewId);
        const text = await response.json();
        const fetchedImages = [];
        for(let i = 0; i < text.length; i++) {
          fetchedImages.push(text[i].savedFileName);
        }
        console.log(fetchedImages);
        setImages(fetchedImages);
      } catch (error) {
        //console.error('Error fetching image:', error);
      }
    };
    fetchImage();
  }, [review.viewId]);

  return (
    <ReviewBlock>
      <TextGray><hr/></TextGray>
      <Horizon>
      <Name>덕우</Name>
      <Star>
        <Horizon>&nbsp;&nbsp;{stars}
      <BlankStar>{blankstars}</BlankStar>
      <Bold>&nbsp;{review.star}</Bold>
        </Horizon>
      </Star>
      </Horizon>
      <Content>
      {images ? (
        images.map(image => (
          <Image src={`/images_review/${image}`} alt="review image" />
        ))
      ) : ( <></>)}
      </Content>
      <Content>{review.content}</Content>
      <ReviewDate>{date}</ReviewDate>
    </ReviewBlock>
  );
}

export default Review;