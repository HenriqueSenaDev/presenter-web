import loadingGif from "assets/images/loading.gif";
import './styles.css';

const Loading = () => {
   return (
      <div className="loading--page">
         <h1>Presenter</h1>

         <img src={loadingGif} alt="loading-gif" ></img>
      </div>
   );
}

export default Loading;