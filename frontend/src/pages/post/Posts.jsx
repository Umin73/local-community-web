import React, { useState, useEffect } from "react";
import { useNavigate, useLocation, useParams } from "react-router-dom";
import PostItem from "../../components/post/PostItem";
import "../../css/Posts.css";
import axios from "axios";

export default function Posts() {
  const { categoryId } = useParams();
  const navigate = useNavigate();
  const location = useLocation();
  const [category, setCategory] = useState(null);


  const query = new URLSearchParams(location.search);
  const initialPage = parseInt(query.get("page")) || 0; // 페이지 번호는 0부터 시작
  const [currentPage, setCurrentPage] = useState(initialPage);

  const [page, setPage] = useState(1);
  const [postList, setPostList] = useState([]);

  useEffect(() => {
    const { category } = location.state || {};
    setCategory(category);

    const fetchData = async () => {
      try {
        const postListResponse = await axios.get(
          `http://localhost:8080/posts/${categoryId}`,
          {
            params: { page: currentPage },
          }
        );
        setPostList(postListResponse.data.content); // 실제 포스트 리스트
      } catch (err) {
        console.log("error : ", err);
      }
    };
    fetchData();
  }, [categoryId, currentPage]);


  useEffect(() => {
    navigate(`?page=${currentPage}`, { replace: true });
  }, [currentPage, navigate]);

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  return (
    <div className="root">
      <h2>{category} 게시판</h2>
      <div className="search">
        <input
          type="text"
          placeholder="글 제목, 내용, 해시태그"
        />
        <button>검색</button>
      </div>
          {postList.map((item, index) => (
              <PostItem key={index} item={item} />
          ))}
        <div className="paginationContainer">{page}</div>
        <div className="posts__btnContainer">
          <button
            onClick={() => {
              navigate("/post/create", {
                state: {
                  categoryId: categoryId,
                  category: category,
                },
              });
            }}
          >
            작성
          </button>
        </div>
      </div>
  );
}
