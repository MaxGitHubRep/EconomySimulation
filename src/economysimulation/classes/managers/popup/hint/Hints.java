package economysimulation.classes.managers.popup.hint;

import economysimulation.classes.managers.popup.hint.hints.ErrorInvalidConnection;
import economysimulation.classes.managers.popup.hint.hints.ErrorInviteSent;
import economysimulation.classes.managers.popup.hint.hints.ErrorNullParty;
import economysimulation.classes.managers.popup.hint.hints.NetworkError;
import economysimulation.classes.managers.popup.hint.hints.ErrorSQLDatabase;
import economysimulation.classes.managers.popup.hint.hints.ErrorServerSlotFull;
import economysimulation.classes.managers.popup.hint.hints.Hint;
import economysimulation.classes.managers.popup.hint.hints.HintBankRateTooHigh;
import economysimulation.classes.managers.popup.hint.hints.HintBankruptcy;
import economysimulation.classes.managers.popup.hint.hints.HintInsufficientFunds;
import economysimulation.classes.managers.popup.hint.hints.HintTaxesTooHigh;
import economysimulation.classes.managers.popup.hint.hints.UserNotFoundError;

/**
 *
 * @author Max Carter
 */
public class Hints {
    
    public static final Hint
            InsufficientFunds = new HintInsufficientFunds(),
            ConsumersBankrupt = new HintBankruptcy("Consumers"),
            CorporationBankrupt = new HintBankruptcy("Corporations"),
            TaxesTooHigh = new HintTaxesTooHigh(),
            InterestRatesTooHigh = new HintBankRateTooHigh(),
            ServerSlotFull = new ErrorServerSlotFull(),
            NotConnected = new ErrorInvalidConnection(),
            DatabaseError = new ErrorSQLDatabase(),
            NoPartyFound = new ErrorNullParty(),
            AlreadyInvited = new ErrorInviteSent(),
            NoUserFound = new UserNotFoundError(),
            NetworkError = new NetworkError();
    
}
