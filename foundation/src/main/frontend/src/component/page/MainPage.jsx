import react from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import './style.css';

import MainTitle from "./MainTitle";
import MainMenu from "./MainMenu";
import MainBoard from "./MainBoard";
import BestBoard from "./BestBoard";
import LeftMenu from "./LeftMenu";

const ContentWrapper = styled.div`
    display: flex;
    justify-content: space-around;
    margin-top: 1%;
`;

function MainPage(props) {
    const {} = props;

    const navigate = useNavigate();

    return (
        <>
            <MainTitle/>
            <hr/>
            <MainMenu/>
            <ContentWrapper>
                <LeftMenu/>
                <MainBoard/>
                <BestBoard/>
            </ContentWrapper>
        </>
    );
}

export default MainPage;