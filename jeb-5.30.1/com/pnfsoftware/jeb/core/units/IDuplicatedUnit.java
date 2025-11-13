package com.pnfsoftware.jeb.core.units;

public interface IDuplicatedUnit extends IUnit {
   String typeDuplicate = "duplicate";

   IUnit getOriginal();
}
