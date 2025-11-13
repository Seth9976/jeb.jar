package com.pnfsoftware.jeb.core.output.tree.impl;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.tree.IActionableNode;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class Node implements IActionableNode {
   @SerId(1)
   private String label;
   @SerId(2)
   private ItemClassIdentifiers classId;
   @SerId(3)
   private long itemId;
   @SerId(4)
   private int flags;
   @SerId(5)
   private int initialExpansion;
   @SerId(6)
   private List children = new ArrayList();

   public Node(String var1) {
      this(var1, null, 0L, 0, 0);
   }

   public Node(String var1, ItemClassIdentifiers var2) {
      this(var1, var2, 0L, 0, 0);
   }

   public Node(String var1, ItemClassIdentifiers var2, long var3, int var5) {
      this(var1, var2, var3, var5, 0);
   }

   public Node(String var1, ItemClassIdentifiers var2, long var3, int var5, int var6) {
      this.label = var1;
      this.classId = var2;
      this.itemId = var3;
      this.flags = var5;
      this.initialExpansion = var6;
   }

   @Override
   public String getLabel() {
      return this.label;
   }

   public void setLabel(String var1) {
      this.label = var1;
   }

   @Override
   public String[] getAdditionalLabels() {
      return null;
   }

   public void addChild(Node var1) {
      this.children.add(var1);
   }

   public void insertChild(int var1, Node var2) {
      this.children.add(var1, var2);
   }

   public void removeChild(Node var1) {
      this.children.remove(var1);
   }

   public void removeChild(int var1) {
      this.children.remove(var1);
   }

   public Node getChild(int var1) {
      return (Node)this.children.get(var1);
   }

   @Override
   public List getChildren() {
      return this.children;
   }

   @Override
   public ItemClassIdentifiers getClassId() {
      return this.classId;
   }

   public void setClassId(ItemClassIdentifiers var1) {
      this.classId = var1;
   }

   @Override
   public long getItemId() {
      return this.itemId;
   }

   public void setItemId(long var1) {
      this.itemId = var1;
   }

   @Override
   public int getItemFlags() {
      return this.flags;
   }

   public void setItemFlags(int var1) {
      this.flags = var1;
   }

   @Override
   public int getInitialExpansion() {
      return this.initialExpansion;
   }

   public void setInitialExpansion(int var1) {
      this.initialExpansion = var1;
   }
}
