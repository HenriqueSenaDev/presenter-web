import { IEvent, IParticipation } from "common/@Interfaces";
import { createContext, useState, useContext } from "react"
import { usePresenter } from "hooks/usePresenter";
import { Context } from "context/ProfileContext";

interface IPresenterContext {
    participations: IParticipation[], handleParticipations: Function,
    event: IEvent | null, handleEvent: Function
}

const PresenterContext = createContext<IPresenterContext>({} as IPresenterContext);

function PresenterContextProvider({ children }: { children: React.ReactNode }) {
    const [participations, setParticipations] = useState<IParticipation[]>([]);
    const [event, setEvent] = useState<IEvent | null>(null);

    const { user } = useContext(ProfileContext);
    const { findUserParticipations, findEvent } = usePresenter();

    async function handleParticipations() {
        setParticipations(await findUserParticipations(user!.id));
    }

    function handleEvent() {

    }

    return (
        <PresenterContext.Provider
            value={{
                participations, handleParticipations,
                event, handleEvent
            }}
        >
            {children}
        </PresenterContext.Provider>
    )
}

export { PresenterContext, PresenterContextProvider }