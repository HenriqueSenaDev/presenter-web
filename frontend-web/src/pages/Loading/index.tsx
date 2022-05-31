import React from 'react';
import './styles.css';
import WhiteGif from "assets/images/white-Gif.gif";

const Loading = () => {
   return (
      <div className="loading--page">
         <h1>Presenter</h1>
         <img src={WhiteGif} alt="loading-gif" ></img>
      </div>
   );
}

export default Loading;