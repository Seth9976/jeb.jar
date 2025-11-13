package com.pnfsoftware.jeb.client.api;

import com.pnfsoftware.jeb.core.units.UnitAddress;

public abstract class GraphDialogExtensions {
   public GraphDialogExtensions.LayoutMode getLayoutMode() {
      return null;
   }

   public boolean processNewVertexName(int var1, String var2) {
      return true;
   }

   public UnitAddress getUnitAddress(int var1) {
      return null;
   }

   public String getDescription(int var1) {
      return null;
   }

   public int getEdgeColor(int var1, int var2) {
      return -1;
   }

   public GraphDialogExtensions.EdgeStyle getEdgeStyle(int var1, int var2) {
      return null;
   }

   public int getVertexColor(int var1) {
      return -1;
   }

   public GraphDialogExtensions.VertexShape getVertexShape(int var1) {
      return null;
   }

   public boolean getVertexMark(int var1) {
      return false;
   }

   public boolean processVertexMark(int var1, boolean var2) {
      return false;
   }

   public boolean getShowVertex(int var1) {
      return true;
   }

   public int getCanvasNodeDensity() {
      return -1;
   }

   public Integer displayEdgesOrientation() {
      return null;
   }

   public static enum EdgeStyle {
      SOLID,
      DASHED,
      DOTTED;
   }

   public static enum LayoutMode {
      FDC,
      FDC_NO_WEIGHT;
   }

   public static enum VertexShape {
      CIRCLE_FILLED,
      SQUARE_FILLED,
      CIRCLE,
      SQUARE;
   }
}
