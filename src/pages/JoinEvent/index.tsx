import Navbar from "../../components/Navbar";
import JoinCard from "./JoinCard";
import "./styles.css";

const JoinEvent = () => {
    return (
        <div className="join--event--container">
            <Navbar />
            <JoinCard />
        </div>
    );
}

export default JoinEvent;