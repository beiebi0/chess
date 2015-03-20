package org.longjuntan.hw3;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface GameImages extends ClientBundle {
  @Source("/Image/black_tile.png")
  ImageResource blackTile();
  
  @Source("/Image/white_tile.png")
  ImageResource whiteTile();
  
  @Source("/Image/Black B.png")
  ImageResource blackBishop();
  
  @Source("/Image/Black K.png")
  ImageResource blackKing();
  
  @Source("/Image/Black N.png")
  ImageResource blackKnight();
   
  @Source("/Image/Black P.png")
  ImageResource blackPawn();
  
  @Source("/Image/Black Q.png")
  ImageResource blackQueen();  
  
  @Source("/Image/Black R.png")
  ImageResource blackRook();
  
  @Source("/Image/White B.png")
  ImageResource whiteBishop();
  
  @Source("/Image/White K.png")
  ImageResource whiteKing();
  
  @Source("/Image/White N.png")
  ImageResource whiteKnight();
   
  @Source("/Image/White P.png")
  ImageResource whitePawn();
  
  @Source("/Image/White Q.png")
  ImageResource whiteQueen();  
  
  @Source("/Image/White R.png")
  ImageResource whiteRook();
  
  @Source("/Image/blank.png")
  ImageResource blank();
}
