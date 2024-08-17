import React from "react";
import "../../css/Posts.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";

export default function PostItem({ item }) {
  const navigate = useNavigate();

  const handleItemClick = () => {
    // 유저 아이디 임시 설정
    axios
      .get(`http://localhost:8080/post/${item.postId}`, {
        params: { userId: 1 },
      })
      .then((response) => {
        const post = response.data;
        navigate(`/post/${post.postId}`, { state: { post: post } });
      })
      .catch((err) => {
        console.log("error : ", err);
      });
  };
  return (
    <div className="posts__item" onClick={handleItemClick}>
      <div className="posts__title">{item.title}</div>
      <div className="posts__content">{item.content}</div>
      <ul className="posts__status">
        <li>
          <img
            src="https://town-in.s3.ap-northeast-2.amazonaws.com/home/like.png"
          />
          {item.likeCount}
        </li>
        <li>
          <img
            src="https://town-in.s3.ap-northeast-2.amazonaws.com/home/comment.png"
          />
          {item.commentCount}
        </li>
      </ul>
    </div>
  );
}
