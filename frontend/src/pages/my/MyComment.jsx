import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import '../../css/MyPage.css';
import Header from "../../components/my/Header";
import Sidebar from "../../components/my/Sidebar";
import axios from 'axios';
import Pagination from "react-js-pagination";

export default function MyComment() {
    const [commentedPosts, setCommentedPosts] = useState([]);
    const [error, setError] = useState("");
    const rpp = 5; // 한 페이지에 보여줄 댓글 수
    const [page, setPage] = useState(1);
    const [totalItems, setTotalItems] = useState(0); // 전체 댓글 수

    const handlePageChange = (newPage) => {
        setPage(newPage);
    };

    useEffect(() => {
        const fetchCommentedPosts = async () => {
            try {
                // 서버로 현재 페이지와 페이지 크기 전달
                const response = await axios.get('/mypage/comments', {
                    params: { page: page - 1, size: rpp }, // Spring에서는 페이지가 0부터 시작
                    withCredentials: true, // JWT 쿠키 포함
                });

                // 백엔드 응답에서 게시물 데이터와 총 게시물 수를 설정
                setCommentedPosts(response.data.posts); // 댓글 단 글 목록
                setTotalItems(response.data.totalItems); // 전체 댓글 수
            } catch (error) {
                setError('Failed to fetch commented posts.');
                console.error('Fetch error:', error);
            }
        };

        fetchCommentedPosts();
    }, [page]); // 페이지 번호가 변경될 때마다 실행

    return (
        <>
            <div className="root-wrap">
                <Header />
            </div>
            <div className="side-wrap">
                <Sidebar />
            </div>
            <MyCommentWrapper>
                <Title>댓글 단 글</Title>
                {error && <ErrorMsg>{error}</ErrorMsg>}
                <Table>
                    <tbody>
                    {commentedPosts.length > 0 ? (
                        commentedPosts.map(post => (
                            <tr key={post.id}>
                                <td>{post.title}</td>
                                <td>{post.content}</td>
                                <td>{`${post.createdDate[0]}-${post.createdDate[1]}-${post.createdDate[2]}`}</td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="4">댓글 단 글이 없습니다.</td>
                        </tr>
                    )}
                    </tbody>
                </Table>
                <PgBox>
                    <Pagination
                        activePage={page} // 현재 페이지
                        itemsCountPerPage={rpp} // 한 페이지당 아이템 수
                        totalItemsCount={totalItems} // 전체 아이템 수
                        pageRangeDisplayed={5} // 표시할 페이지 버튼 수
                        onChange={handlePageChange} // 페이지 변경 핸들러
                    />
                </PgBox>
            </MyCommentWrapper>
        </>
    );
}

const MyCommentWrapper = styled.div`
    margin-left: 400px;
    margin-right: 50px;
    justify-content: space-around;
`;

const Title = styled.div`
    font-size: 20px;
    display: inline-block;
`;

const ErrorMsg = styled.div`
    color: red;
    font-size: 14px;
    margin-top: 10px;
`;

const Table = styled.table`
    margin-top: 15px;
    width: 100%;
    border-collapse: collapse;

    tr, td {
        border: 1px solid #ddd;
        padding: 8px;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    tr:hover {
        background-color: #ddd;
    }

    td {
        padding: 12px;
    }
`;

const PgBox = styled.div`
    .pagination {
        display: flex;
        justify-content: center;
        margin-top: 10px;
    }
    
    ul {
        list-style: none;
        padding: 0;
    }

    ul.pagination li {
        display: inline-block;
        width: 20px;
    }

    ul.pagination li a {
        text-decoration: none; 
        color: #484848;
    }
    ul.pagination li.active a {
        color: green;
    }
`;
