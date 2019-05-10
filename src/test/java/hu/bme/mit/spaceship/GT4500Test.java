package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockTS1;
  private TorpedoStore mockTS2;

  @BeforeEach
  public void init(){
    this.mockTS1 = mock(TorpedoStore.class);
    this.mockTS2 = mock(TorpedoStore.class);
    this.ship = new GT4500(mockTS1, mockTS2);
  }

  @Test
  public void fireSecondTorpedo_second(){
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(1)).fire(1);
  }

  @Test
  public void fireFirst_if_second_empty(){
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockTS1, times(2)).fire(1);
    verify(mockTS2, times(0)).fire(1);
  }

  @Test
  public void fireSecond_if_first_empty(){
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockTS1, times(0)).fire(1);
    verify(mockTS2, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Failure(){
    when(mockTS1.isEmpty()).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.ALL);

    verify(mockTS1, times(0)).fire(1);
    verify(mockTS2, times(0)).fire(1);
  }

 @Test
  public void fireTorpedo_All_Failure_If_First_Empty(){
    when (mockTS1.isEmpty()).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.ALL);

    verify(mockTS1, times(0)).fire(1);
    verify(mockTS2, times(0)).fire(1);
  }

  @Test
  public void fireLaser_not_implemented(){
    boolean result = ship.fireLaser(FiringMode.SINGLE);

    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockTS1.fire(1)).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockTS1, times(1)).fire(1);
    verify(mockTS2, times(1)).fire(1);
  }

}
