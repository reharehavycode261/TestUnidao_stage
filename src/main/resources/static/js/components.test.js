const assert = require('assert');
const { Region, Territorie } = require('project/entity');

describe('Entity toString() methods', function () {
    it('should correctly format Region to string', function () {
        const region = new Region('R001', 'North Region');
        assert.strictEqual(region.toString(), "Region{regionId='R001', regionDescription='North Region'}");
    });

    it('should correctly format Territorie to string', function () {
        const territorie = new Territorie('T001', 'South Territorie');
        assert.strictEqual(territorie.toString(), "Territorie{territorieId='T001', territorieDescription='South Territorie'}");
    });
});