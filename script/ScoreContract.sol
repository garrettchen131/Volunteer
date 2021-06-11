pragma solidity>=0.4.24 <0.6.11;

import "./Table.sol";

contract ScoreContract {

    string constant TABLE_NAME = "t_score";

    TableFactory tableFactory;

    constructor() public {
        tableFactory = TableFactory(0x1001);
        tableFactory.createTable(TABLE_NAME, "idCard", "score");
    }

    function signAccount(string memory idCard) returns(int256) {
        Table table = tableFactory.openTable(TABLE_NAME);
        Entries entries = table.select(idCard, table.newCondition());
        if(entries.size() != 0) {
            return -1;
        }
        Entry entry = table.newEntry();
        entry.set("idCard", idCard);
        int256 score = 0;
        entry.set("score", score);
        int256 count = table.insert(idCard, entry);
        return count;
    }

    function getScore(string memory idCard) view returns(int256) {
        Table table = tableFactory.openTable(TABLE_NAME);
        Entries entries = table.select(idCard, table.newCondition());
        if(entries.size() == 0) {
            return -1;
        }
        Entry entry = entries.get(0);
        int256 score = entry.getInt("score");
        return score;
    }

    function addScore(string memory idCard, int256 score) returns(int256) {
        Table table = tableFactory.openTable(TABLE_NAME);
        Entries entries = table.select(idCard, table.newCondition());
        if(entries.size() == 0) {
            return -1;
        }
        Entry entry = entries.get(0);
        int256 origin = entry.getInt("score");
        int256 newScore = score + origin;
        Entry newEntry = table.newEntry();
        newEntry.set("score", newScore);
        Condition condition = table.newCondition();
        condition.EQ("idCard", idCard);
        int256 count = table.update(idCard, newEntry, condition);
        return count;
    }
}
