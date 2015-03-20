package org.wenjiechen.hw3;

import org.shared.chess.Color;
import org.shared.chess.GameResult;
import org.shared.chess.Move;
import org.shared.chess.Piece;
import org.shared.chess.State;

import org.wenjiechen.hw3.Presenter.View;
import org.wenjiechen.hw5.FadeAnimation;
import org.wenjiechen.hw5.ResizeAnimation;
import org.wenjiechen.hw5.SlowMoveAnimation;
import org.wenjiechen.hw6.client.ChessGameService;
import org.wenjiechen.hw6.client.ChessGameServiceAsync;
import org.wenjiechen.hw8.LanguageChinese;

import com.google.gwt.appengine.channel.client.Channel;
import com.google.gwt.appengine.channel.client.ChannelError;
import com.google.gwt.appengine.channel.client.ChannelFactoryImpl;
import com.google.gwt.appengine.channel.client.Socket;
import com.google.gwt.appengine.channel.client.SocketListener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.event.dom.client.HasAllDragAndDropHandlers;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.media.client.Audio;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.contacts.Contact;
import com.googlecode.gwtphonegap.client.contacts.ContactError;
import com.googlecode.gwtphonegap.client.contacts.ContactField;
import com.googlecode.gwtphonegap.client.contacts.ContactFindCallback;
import com.googlecode.gwtphonegap.client.contacts.ContactFindOptions;
import com.googlecode.gwtphonegap.client.util.PhonegapUtil;
import com.googlecode.gwtphonegap.collection.shared.CollectionFactory;
import com.googlecode.gwtphonegap.collection.shared.LightArray;


public class Graphics extends Composite implements View {
	private static GameImages gameImages = GWT.create(GameImages.class);
	private static GraphicsUiBinder uiBinder = GWT.create(GraphicsUiBinder.class);
	private static ChessGameServiceAsync CGService = GWT.create(ChessGameService.class);
	private static LanguageChinese LC = GWT.create(LanguageChinese.class);
	final PhoneGap phoneGap = GWT.create(PhoneGap.class);


	interface GraphicsUiBinder extends UiBinder<Widget, Graphics> {
	}

	@UiField
	GameCss css;
	@UiField
	Label gameStatus;
	@UiField
	Label gameTurn;
	@UiField
	Label gameResult;
	@UiField
	Label promotionPromt;
	@UiField
	Grid gameGrid;
	@UiField
	Grid promotionGrid;
//	@UiField
//	Button save;
//	@UiField
//	Button load;
//	@UiField
//	Button restart;
	@UiField
	static VerticalPanel boardPanel;
	@UiField
	Label userStatus;
	@UiField
	Label opponentStatus;
	@UiField
	Anchor logOutLink;
	@UiField
	Button autoMatch; //auto match
	@UiField
	Button DeleteMatch;
	
	//hw7
	@UiField
	ListBox matchList;
//	@UiField
//	TextBox inputEmail;
	@UiField
	Button emailMatch;//invite button
	@UiField
	Button loadMatch;
	
	//hw9
	@UiField
	Button PlayWithAI;
	
	//hw11
	@UiField(provided = true)
	SuggestBox emailMatchSuggestBox;// = new SuggestBox();
	
	private Image[][] board = new Image[8][8];
	private Image[] promotionImage = new Image[4];
	private Presenter presenter;
	private Audio dropPiece = creatDropAudio();

	private Socket socket;
	private SocketListener socketListener = null;
	
	private AsyncCallback<Void> nullCallback = new AsyncCallback<Void>() {
		@Override
		public void onFailure(Throwable caught){}
		
		@Override
		public void onSuccess(Void result){}		
	};	
	
	//hw11
    private final MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
    String playerEmail = "";
	
	public Graphics() {
		//hw11
		oracle.add("wenjie1@gmail.com");
		oracle.add("wenjie2@gmail.com");
		oracle.add("wenjie3@gmail.com");
		emailMatchSuggestBox = new SuggestBox(oracle);
		
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void initial(){
		autoMatch.setText(LC.matchButton());
		logOutLink.setText(LC.logOutLink());
		DeleteMatch.setText(LC.deleteMatchButton());
		loadMatch.setText(LC.LoadMatchButton());
		emailMatch.setText(LC.emailMatchButton());		
		
		
		gameGrid.resize(8, 8);
		gameGrid.setCellPadding(0);
		gameGrid.setCellSpacing(0);
		gameGrid.setBorderWidth(0);
		gameStatus.setText(LC.gameStar());
		gameTurn.setText(LC.whiteTurn());
		

		
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				final Image image = new Image();
				board[row][col] = image;
				image.setWidth("100%");
				final int r = row;
				final int c = col;
				image.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				image.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.dealWithClick(r,c);
					}
				});
				
				if (row % 2 == 0 && col % 2 == 1 || row % 2 == 1
						&& col % 2 == 0) {
					image.setResource(gameImages.blackTile());
				} else {
					image.setResource(gameImages.whiteTile());
				}
				gameGrid.setWidget(row, col, image);
			}
		}
		
		promotionGrid.resize(1, 4);
		promotionGrid.setCellPadding(0);
		promotionGrid.setCellSpacing(0);
		promotionGrid.setBorderWidth(0);

		for (int i = 0; i < 4; ++i) {
			final Image image = new Image();
			promotionImage[i] = image;
			image.setWidth("100%");
			final int pos = i;
			image.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					presenter.getPromotionKind(pos);
				}
			});
			image.setResource(gameImages.whiteTile());
			promotionGrid.setWidget(0, i, image);
		}// for
		
//		AsyncCallback<String> connectCallback = new AsyncCallback<String>() {
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert(caught.getMessage());
//				Window.alert(LC.errorCannotLogInServer());
//			}
//			
//			@Override
//			public void onSuccess(String result) {
////				System.out.println("onSuccess() result" + result );
//				String[] tokenAndPlayerInfo = result.split(">");
//				String token = tokenAndPlayerInfo[0];
//				if (token != null) {
//					Channel channel = new ChannelFactoryImpl().createChannel(token);
//					socket = channel.open(socketListener);
//				}
//				presenter.parseMessageForLoginPlayer(tokenAndPlayerInfo[1]);
//			}
//		};		
//		
//		CGService.creatChannelForLoggedInUser(playerEmail,connectCallback);
				
		logOutLink.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event){
//				CGService.deleteMatch(nullCallback);
//				CGService.removeUserFromWaitinglist(nullCallback);
//				Window.alert(LC.romoveUserFromWaitingList());
				socket.close();
			}
		});

		emailMatch.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String email = emailMatchSuggestBox.getText();
				CGService.emailMatch(playerEmail, email,new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(LC.errorCannotInvitePlayerViaEmail());
					}

					@Override
					public void onSuccess(Void result) {
//						System.out.println("updateMatchList in emailMatch");
//						presenter.updateMatchList(); //update in every get message channel
					}
				});
			}
		});
		
		((ServiceDefTarget) CGService).setServiceEntryPoint("http://chess-wenjie-hw11.appspot.com/wenjiechen/chessGames");
		PhonegapUtil.prepareService((ServiceDefTarget) CGService, "http://chess-wenjie-hw11.appspot.com/", "wenjiechen/chessGames");

		getContacts();		
}
	
	public void setPresenter(Presenter presenter){
		this.presenter = presenter;
//		boardPanel.add(emailMatchSuggestBox);
	}
	
	@Override
	public void setPiece(int row, int col, Piece piece) {
		
		if (piece != null) {
			switch (piece.getKind()) {
			case KING:
				if (piece.getColor() == Color.WHITE) {
					board[row][col].setResource(gameImages.whiteKing());
				} else {
					board[row][col].setResource(gameImages.blackKing());
				}
				break;
			case ROOK:
				if (piece.getColor() == Color.WHITE) {
					board[row][col].setResource(gameImages.whiteRook());
				} else {
					board[row][col].setResource(gameImages.blackRook());
				}
				break;
			case BISHOP:
				if (piece.getColor() == Color.WHITE) {
					board[row][col].setResource(gameImages.whiteBishop());
				} else {
					board[row][col].setResource(gameImages.blackBishop());
				}
				break;
			case KNIGHT:
				if (piece.getColor() == Color.WHITE) {
					board[row][col].setResource(gameImages.whiteKnight());
				} else {
					board[row][col].setResource(gameImages.blackKnight());
				}
				break;
			case QUEEN:
				if (piece.getColor() == Color.WHITE) {
					board[row][col].setResource(gameImages.whiteQueen());
				} else {
					board[row][col].setResource(gameImages.blackQueen());
				}
				break;
			case PAWN:
				if (piece.getColor() == Color.WHITE) {
					board[row][col].setResource(gameImages.whitePawn());
				} else {
					board[row][col].setResource(gameImages.blackPawn());
				}
				break;
			}// switch
		}// if
		else {
			if (row % 2 == 0 && col % 2 == 1 || row % 2 == 1 && col % 2 == 0) {
				board[row][col].setResource(gameImages.blackTile());
			} else {
				board[row][col].setResource(gameImages.whiteTile());
			}
		}// else
	}

	@Override
	public HasAllDragAndDropHandlers addDnDListener(int row, int col){
		return board[row][col];
	}
	
	@Override
	public void setHighlighted(int row, int col, boolean highlighted) {
		Element element = board[row][col].getElement();
		if (highlighted) {
			element.setClassName(css.highlighted());
		} else {
			element.removeClassName(css.highlighted());
		}		
	}

	@Override
	public void setLastMove(int row, int col, boolean lastmove) {
		Element elem = board[row][col].getElement();
		if (lastmove) {
			elem.setClassName(css.lastmove());
		} else {
			elem.removeClassName(css.lastmove());
		}
	}

	@Override
	public void setWhoseTurn(Color color) {
		if (color != null) {
			if (color == Color.WHITE) {
				gameTurn.setText(LC.whiteTurn());
			} else {
				gameTurn.setText(LC.blackTurn());
			}
		}
	}

	@Override
	public void setGameResult(GameResult gameResult) {
		if (gameResult != null) {
			this.gameResult.setText(LC.resultWinner() + gameResult.getWinner() + LC.resultResaon() + gameResult.getGameResultReason());
		}
	}

	@Override
	public void setPromotionChoice(Color color) {
		promotionPromt.setText(LC.promotionPromt());
//		gameGrid.setWidget(2, 3, promotionGrid);
		promotionGrid.setVisible(true);

		if (color == Color.WHITE) {
			promotionImage[0].setResource(gameImages.whiteBishop());
			promotionImage[1].setResource(gameImages.whiteKnight());
			promotionImage[2].setResource(gameImages.whiteRook());
			promotionImage[3].setResource(gameImages.whiteQueen());
		} else {
			promotionImage[0].setResource(gameImages.blackBishop());
			promotionImage[1].setResource(gameImages.blackKnight());
			promotionImage[2].setResource(gameImages.blackRook());
			promotionImage[3].setResource(gameImages.blackQueen());
		}
		for (int i = 0; i < 4; ++i) {
			Element element = promotionImage[i].getElement();
			element.setClassName(css.highlighted());
		}
	}
	
	@Override
	public void setPromotionPromt(String str){
		promotionPromt.setText(str);	
	}
	
	@Override
	public void cleanPromotionPanel(){
		promotionPromt.setText(" ");
		gameGrid.remove(promotionGrid);
		promotionGrid.setVisible(false);
		for (int i = 0; i < 4; ++i) {
			Element element = promotionImage[i].getElement();
			element.removeClassName(css.highlighted());
		}
	}
	
	@Override
	public void saveHistory(String state) {
		History.newItem(state);
	}
	
	@Override
	public void audioPlay(){
		dropPiece.play();
	}
	
//	@Override
//	public HasClickHandlers getSaveButton(){
//		return save;
//	}
//	
//	@Override
//	public HasClickHandlers getLoadButton(){
//		return load;
//	}
//	
//	@Override
//	public HasClickHandlers getRestartButton(){
//		return restart;
//	}
	
	@Override
	public Widget getImage(int r, int c){
		return gameGrid.getWidget(r, c);
	}

	@Override
	public Grid getGameBoard(){
		return gameGrid;
	}
	
	@Override
	public void newGridBoard(){
		gameGrid = new Grid();
		gameGrid.resize(8, 8);
		gameGrid.setCellPadding(0);
		gameGrid.setCellSpacing(0);
		gameGrid.setBorderWidth(0);
	}
	
	@Override
	public void playFadeAnimation(Move move){
		Widget image = gameGrid.getWidget(move.getTo().getRow(), move.getTo().getCol());
		final Move mv = move;
		FadeAnimation fadeAni = new FadeAnimation(image){
			@Override
			protected void onStart(){
				super.onStart();
				presenter.refreshState(mv);
			}
		};
		fadeAni.run(800);
	}
	
	@Override
	public void playSlowMoveAnimation(Move move) {
		final Move mv = move;
		SlowMoveAnimation slowMoveAni = new SlowMoveAnimation(board, move) {
			@Override
			protected void onComplete() {
				super.onComplete();
				presenter.refreshState(mv);
			}
		};
		slowMoveAni.run(800);
	}
	
	public Audio creatDropAudio() {
		Audio audio = Audio.createIfSupported();
		if (audio == null) {
			return null;
		}
		audio.addSource("wenjiechn_audio/drop.mp3");
		audio.setControls(true);
		return audio;
	}
	
	@Override
	public void playMoveSound(){
		if(dropPiece != null){
//			System.out.println("playmovesound()");
			dropPiece.play();
		}		
	}
	
	@Override
	public void addSocketListener(SocketListener listener) {
		socketListener = listener;
	}

	@Override
	public void autoMatch() {
		CGService.autoMatch(playerEmail,nullCallback);
	}

	@Override
	public void sendMoveAndState(String move) {
		CGService.sendMoveAndState(playerEmail, move, nullCallback);
	}

	@Override
	public void setPlayer(String name,String color, int rank){
//		System.out.println("setPlayer()");
		userStatus.setText(LC.setPlayerName()+ name + LC.setPlayerColor() + color + LC.setPlayerRank()+ rank);
	}
	
	@Override
	public void setOpponentPlayer(String name,String color, String rank){
		opponentStatus.setText(LC.setOppPlayerName()+ name + LC.setOppPlayerColor() + color + LC.setOppPlayerRank() + rank);
	}
	
	@Override
	public void setLogoutURL(String logoutLink){
		logOutLink.setHref(logoutLink);
	}
	
	@Override
	public void cleanMatchList(){
		matchList.clear();
	}
	
	@Override
	public void addItemToMatchList(String str){
		matchList.addItem(str);
		matchList.setVisibleItemCount(1);		
	}
	
	@Override
	public HasChangeHandlers getMatchList() {
		return matchList;
	}
	
	@Override
	public int getIndexofSelectionFromMatchList() {
		int idx =  matchList.getSelectedIndex();
		//print out the matchList
//		System.out.println("getIndexofSelectionFromMatchList = " + idx);
		return idx;
	}
	
	@Override
	public void fetchMatchList(String email, AsyncCallback<String> matchListCallback) {
		CGService.fetchMatchList(email, matchListCallback);
	}
	
	@Override
	public HasClickHandlers getLoadMatchButton(){
		return loadMatch;
	}
	
	@Override
	public void calculateRank(String matchid, String gameResult, AsyncCallback<Void> callback){
		CGService.calculateRank(matchid, gameResult, callback);
	}
	
	@Override
	public HasClickHandlers getAutomatchButton(){
		return autoMatch;
	}
	
	@Override
	public HasClickHandlers getDeleteMatchButton(){
		return DeleteMatch;
	}

	@Override
	public void deleteMatch(String matchid) {
		CGService.deleteMatch(matchid,nullCallback);
	}

	@Override
	public HasClickHandlers getPlayWithAI(){
		return PlayWithAI;
	}
	
	@Override
	public void addToOracle(String email){
		oracle.add(email);
	}
	
	@Override
	public void getPlayerEmail(String email){
		playerEmail = email;
		System.out.println("1 getPlayerEmail( ) : " + playerEmail);
		System.out.println("modify 5: ");
		
		AsyncCallback<String> connectCallback = new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage()); //RPC phoneGap have problem
				Window.alert(LC.errorCannotLogInServer());
			}
			
			@Override
			public void onSuccess(String result) {
//				System.out.println("onSuccess() result" + result );
				String[] tokenAndPlayerInfo = result.split(">");
				String token = tokenAndPlayerInfo[0];
				if (token != null) {
					Channel channel = new ChannelFactoryImpl().createChannel(token);
					socket = channel.open(socketListener);
				}
				presenter.parseMessageForLoginPlayer(tokenAndPlayerInfo[1]);
			}
		};
//		System.out.println("2 getPlayerEmail( ) : ");
		CGService.creatChannelForLoggedInUser(playerEmail,connectCallback);
//		System.out.println("2 getPlayerEmail( ) : ");
	}
	
	private void getContacts() {
		LightArray<String> fields = CollectionFactory.<String> constructArray();
		fields.push("emails");
		ContactFindOptions findOptions = new ContactFindOptions("", true);
		phoneGap.getContacts().find(fields, new ContactFindCallback() {
			@Override
			public void onSuccess(LightArray<Contact> contacts) {
//				Window.alert("1 get contacts onSuccess lenght = " + contacts.length());
				for (int i = 0; i < contacts.length(); i++) {
					LightArray<ContactField> emails = contacts.get(i)
							.getEmails();
					for (int j = 0; j < emails.length(); j++) {
						ContactField email = emails.get(j);
						oracle.add(email.getValue());						
//						addToOracle(email.getValue());
					}
				}
//				Window.alert("2 get contacts onSuccess finish");
			}

			@Override
			public void onFailure(ContactError error) {
				// something went wrong, doh!
				Window.alert("phonegap get contacts faild");
			}
		}, findOptions);
	}
	
}