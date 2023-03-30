import { createContext, useState} from "react"
import { IEvent, IParticipation } from "common/@types/presenter.types";
import { usePresenter } from "hooks/usePresenter";

interface IPresenterContext {
    participations: IParticipation[], handleParticipations: Function,
    event: IEvent | null, handleEvent: Function
}

const PresenterContext = createContext<IPresenterContext>({} as IPresenterContext);

function PresenterContextProvider({ children }: { children: React.ReactNode }) {
    const [participations, setParticipations] = useState<IParticipation[]>([]);
    const [event, setEvent] = useState<IEvent | null>(null);

    const { findUserParticipations, findEvent } = usePresenter();

    async function handleParticipations() {
        setParticipations(await findUserParticipations());
    }

    async function handleEvent(eventId: number) {
        setEvent(await findEvent(eventId));
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