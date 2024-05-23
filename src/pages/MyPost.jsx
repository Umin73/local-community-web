import React from "react";
import styled from "styled-components";
import { myPostData } from "../data/myPostData";

export default function MyPost() {
  return (
    <>
      <Mypost>
        <Title>내가 쓴 글</Title>
        <div>
          {myPostData.map((data) => {
            return (
              <>
                <Box>
                  <Board>{data.board + " 게시판"}</Board>
                  <Content>{data.content}</Content>
                  <p />
                  <p />
                  <p />
                  <Bottom>
                    <Date>{data.date}</Date>
                    <LikeComment>
                      {"좋아요 " + data.likes}
                      &nbsp;&nbsp;&nbsp;&nbsp;
                      {"댓글 " + data.commentCount}
                    </LikeComment>
                  </Bottom>
                </Box>
              </>
            );
          })}
        </div>
      </Mypost>
    </>
  );
}

const Mypost = styled.div`
  margin-left: 350px;
  margin-right: 50px;
  width: 60%;
  justify-content: space-around;
`;

const Title = styled.div`
  font-weight: bold;
  font-size: 20px;
  margin-bottom: 20px;
`;

const Box = styled.div`
  border: 1px solid #989898;
  padding: 10px;
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
