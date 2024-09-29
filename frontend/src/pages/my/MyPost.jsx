import React, { useEffect, useState } from "react";
import styled from "styled-components";
import axios from "axios";
import Header from "../../components/my/Header";
import Sidebar from "../../components/my/Sidebar";
import '../../css/MyPage.css';

export default function MyPost() {
    const [currentPost, setCurrentPost] = useState([]); // 한 페이지에 보여지는 포스트
    const userId = 1; // 예시로 사용자 ID를 설정

    useEffect(() => {
        // 특정 postId에 해당하는 게시물 데이터를 가져옵니다.
        const fetchPostById = async () => {
            try {
                const postId = 5; // 예시로 postId를 설정
                const response = await axios.get(`/post/${postId}`, {
                    params: {
                        userId: userId,  // 현재 사용자의 ID를 요청에 포함
                    },
                });
                setCurrentPost([response.data]); // 서버에서 가져온 게시물 데이터 설정
            } catch (error) {
                console.error("Failed to fetch post by id:", error);
            }
        };

        fetchPostById();
    }, []); // 컴포넌트가 마운트될 때 호출

    return (
        <>
            <div className="root-wrap">
                <Header/>
            </div>
            <div className="side-wrap">
                <Sidebar/>
            </div>
            <Mypost>
                <Title>내가 쓴 글</Title>
                <div>
                    {currentPost.map((data) => {
                        return (
                            <Box key={data.postId}>
                                <Board>{data.title}</Board>
                                <Content>{data.content}</Content>
                                <Bottom>
                                    <Date>{data.createdDate}</Date>
                                    <LikeComment>
                                        {"좋아요 " + data.likeCount}
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        {"댓글 " + data.commentCount}
                                    </LikeComment>
                                </Bottom>
                            </Box>
                        );
                    })}
                </div>
            </Mypost>
        </>
    );
}

// 스타일 컴포넌트 정의
const Mypost = styled.div`
    margin-left: 400px;
    margin-right: 50px;
    justify-content: space-around;
`;

const Title = styled.div`
    font-size: 20px;
    display: inline-block;
`;

const Box = styled.div`
  border: 1px solid #989898;
  padding: 10px;
  margin-top : 15px;
  margin-bottom: 15px;
`;

const Board = styled.div`
  font-weight: bold;
  color: #043400;
`;

const Content = styled.div`
  margin-top: 10px;
  margin-bottom: 25px;
`;

const Date = styled.div`
  display: inline-block;
`;

const LikeComment = styled.div`
  float: right;
  margin-right: 10px;
`;

const Bottom = styled.div`
  font-size: 13px;
  color: #989898;
`;


// import React, { useEffect, useState } from "react";
// import styled from "styled-components";
// import axios from "axios";
// import Pagination from "react-js-pagination";
// import Header from "../../components/my/Header";
// import Sidebar from "../../components/my/Sidebar";
// import '../../css/MyPage.css';
//
// export default function MyPost() {
//     const rpp = 5; // 한 페이지에 5개씩 표시
//     const [currentPost, setCurrentPost] = useState([]); // 한 페이지에 보여지는 포스트
//     const [page, setPage] = useState(1); // 현재 페이지
//     const [totalPosts, setTotalPosts] = useState(0); // 총 게시물 수
//
//     const userId = 1; // 예시로 사용자 ID를 설정. 실제로는 인증된 사용자 ID를 사용해야 함
//
//     const handlePageChange = (page) => {
//         setPage(page);
//     };
//
//     useEffect(() => {
//         // 현재 페이지의 게시물 데이터를 가져옵니다.
//         const fetchPosts = async () => {
//             try {
//                 const response = await axios.get(`/mypage/posts`, {
//                     params: {
//                         id: userId,
//                         page: page - 1, // 서버에서 페이지 인덱스가 0부터 시작하는 경우를 대비하여 page - 1로 설정
//                         size: rpp, // 한 페이지에 표시할 게시물 수
//                     },
//                 });
//                 setCurrentPost(response.data.content); // 서버에서 가져온 게시물 데이터 설정
//                 setTotalPosts(response.data.totalElements); // 총 게시물 수 설정
//             } catch (error) {
//                 console.error("Failed to fetch posts:", error);
//             }
//         };
//
//         fetchPosts();
//     }, [page]);
//
//     return (
//         <>
//             <div className="root-wrap">
//                 <Header/>
//             </div>
//             <div className="side-wrap">
//                 <Sidebar/>
//             </div>
//             <Mypost>
//                 <Title>내가 쓴 글</Title>
//                 <div>
//                     {currentPost.map((data) => {
//                         return (
//                             <Box key={data.id}>
//                                 <Board>{data.boardName + " 게시판"}</Board>
//                                 <Content>{data.content}</Content>
//                                 <Bottom>
//                                     <Date>{data.createdDate}</Date>
//                                     <LikeComment>
//                                         {"좋아요 " + data.likes}
//                                         &nbsp;&nbsp;&nbsp;&nbsp;
//                                         {"댓글 " + data.commentCount}
//                                     </LikeComment>
//                                 </Bottom>
//                             </Box>
//                         );
//                     })}
//                     <PgBox>
//                         <Pagination
//                             activePage={page}
//                             itemsCountPerPage={rpp}
//                             totalItemsCount={totalPosts}
//                             pageRangeDisplayed={5}
//                             onChange={handlePageChange}
//                         />
//                     </PgBox>
//                 </div>
//             </Mypost>
//         </>
//     );
// }
//
// const Mypost = styled.div`
//     margin-left: 400px;
//     margin-right: 50px;
//     justify-content: space-around;
// `;
//
// const Title = styled.div`
//     font-size: 20px;
//     display: inline-block;
// `;
//
// const Box = styled.div`
//   border: 1px solid #989898;
//   padding: 10px;
//   margin-top : 15px;
//   margin-bottom: 15px;
// `;
//
// const Board = styled.div`
//   font-weight: bold;
//   color: #043400;
// `;
//
// const Content = styled.div`
//   margin-top: 10px;
//   margin-bottom: 25px;
// `;
//
// const Date = styled.div`
//   display: inline-block;
// `;
//
// const LikeComment = styled.div`
//   float: right;
//   margin-right: 10px;
// `;
//
// const Bottom = styled.div`
//   font-size: 13px;
//   color: #989898;
// `;
//
// const PgBox = styled.div`
//     .pagination {
//         display: flex;
//         justify-content: center;
//         margin-top: 10px;
//     }
//
//     .pagination a {
//         border : 0;
//     }
//
//     ul {
//         list-style: none;
//         padding: 0;
//     }
//
//     ul.pagination li {
//         display: inline-block;
//         width: 20px;
//     }
//
//     ul.pagination li a {
//         text-decoration: none;
//         color: #484848;
//     }
//
//     ul.pagination li.active a {
//         color: green;
//     }
// `;
//
//
//
// // import React, { useEffect, useState } from "react";
// // import styled from "styled-components";
// // import { myPostData } from "../../data/myPostData";
// // import Pagination from "react-js-pagination";
// // import Header from "../../components/my/Header";
// // import Sidebar from "../../components/my/Sidebar";
// // import '../../css/MyPage.css';
// //
// // export default function MyPost() {
// //   const rpp = 1; //한페이지에 5개씩
// //   const [currentPost, setCurrentPost] = useState([]); //한페이지에 보여지는 포스트
// //   const [page, setPage] = useState(1); //현재페이지
// //
// //   const handlePageChange = (page) => {
// //     setPage(page);
// //   };
// //
// //   const startIndex = (page - 1) * rpp;
// //   const lastIndex = rpp * page;
// //
// //   useEffect(() => {
// //     //myPostData.reverse();
// //     setCurrentPost(myPostData.slice(startIndex, lastIndex));
// //   }, [startIndex, lastIndex, page]);
// //
// //   return (
// //       <>
// //         <div className="root-wrap">
// //           <Header/>
// //         </div>
// //         <div className="side-wrap">
// //           <Sidebar/>
// //         </div>
// //         <Mypost>
// //           <Title>내가 쓴 글</Title>
// //           <div>
// //             {currentPost.map((data) => {
// //               return (
// //                   <>
// //                     <Box>
// //                       <Board>{data.board + " 게시판"}</Board>
// //                       <Content>{data.content}</Content>
// //                       <p/>
// //                       <p/>
// //                       <p/>
// //                       <Bottom>
// //                         <Date>{data.date}</Date>
// //                         <LikeComment>
// //                           {"좋아요 " + data.likes}
// //                           &nbsp;&nbsp;&nbsp;&nbsp;
// //                           {"댓글 " + data.commentCount}
// //                         </LikeComment>
// //                       </Bottom>
// //                     </Box>
// //                   </>
// //               );
// //             })}
// //             <PgBox>
// //               <Pagination
// //                   activePage={page}
// //                   itemsCountPerPage={rpp}
// //                   totalItemsCount={myPostData.length}
// //                   pageRangeDisplayed={5}
// //                   onChange={handlePageChange}
// //               />
// //             </PgBox>
// //           </div>
// //         </Mypost>
// //       </>
// //   );
// // }
// //
// // const Mypost = styled.div`
// //     margin-left: 400px;
// //     margin-right: 50px;
// //     justify-content: space-around;
// // `;
// //
// // const Title = styled.div`
// //     font-size: 20px;
// //     display: inline-block;
// // `;
// //
// // const Box = styled.div`
// //   border: 1px solid #989898;
// //   padding: 10px;
// //     margin-top : 15px;
// //     margin-bottom: 15px;
// // `;
// //
// // const Board = styled.div`
// //   font-weight: bold;
// //   color: #043400;
// // `;
// //
// // const Content = styled.div`
// //   margin-top: 10px;
// //   margin-bottom: 25px;
// // `;
// //
// // const Date = styled.div`
// //   display: inline-block;
// // `;
// // const LikeComment = styled.div`
// //   float: right;
// //   margin-right: 10px;
// // `;
// // const Bottom = styled.div`
// //   font-size: 13px;
// //   color: #989898;
// // `;
// // const PgBox = styled.div`
// //     .pagination {
// //         display: flex;
// //         justify-content: center;
// //         margin-top: 10px;
// //     }
// //
// //     .pagination a {
// //         border : 0;
// //     }
// //
// //     ul {
// //         list-style: none;
// //         padding: 0;
// //     }
// //
// //     ul.pagination li {
// //         display: inline-block;
// //         width: 20px;
// //     }
// //
// //     ul.pagination li a {
// //         text-decoration: none;
// //         color: #484848;
// //     }
// //
// //     ul.pagination li.active a {
// //         color: green;
// //     }
// //
// // `;
