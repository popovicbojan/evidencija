var sprintApp = angular.module("sprintApp", ["ngRoute"]);

sprintApp.controller("ZadaciCtrl", function($scope, $http, $location) {
    //definisanje url za api
    var urlZadaci = "/api/zadaci";
    var urlSprinteva = "/api/sprintevi";
    var urlStanja = "api/stanja";

    //liste za prihvatanje podataka sa bekenda
    $scope.zadaci = [];
    $scope.sprintevi = [];
    $scope.stanja = [];

    //parametri za pretragu koji se vezu sa formom
    $scope.sParams = {};
    $scope.sParams.idSprinta = "";
    $scope.sParams.imeZadatka = "";

    //parametri za paginaciju
    $scope.pageNum = 0;
    $scope.totalPages = 1;

    //dobavljanje liste glavnog entiteta
    var getZadaci = function() {

        //kofiguracija koja se salje na bekend
        var config = {
            params: {}
        };

        //provera da li su parametri za pretragu uneseni i ako jesu da se salju na bekend
        if ($scope.sParams.idSprinta != "") {
            config.params.idSprinta = $scope.sParams.idSprinta;
        }

        if ($scope.sParams.imeZadatka != "") {
            config.params.imeZadatka = $scope.sParams.imeZadatka;
        }

        //dodavanje parametra za broj strane
        config.params.page = $scope.pageNum;

        var promise = $http.get(urlZadaci, config);
        promise.then(
            function(res) {
                $scope.totalPages = res.headers("ukupnoStrana");
                $scope.zadaci = res.data;
                $scope.sParams.idSprinta = "";
                $scope.sParams.imeZadatka = "";
            },
            function(res) {
                alert("Neuspesno dobavljanje Zadataka!");
            }
        );

    };

    //poziv dobavljanja glavnih entitita
    getZadaci();

    //dobavljanje liste pomocnog entiteta
    var getSprintevi = function() {
        var promise = $http.get(urlSprinteva);

        promise.then(
            function(res) {
                $scope.sprintevi = res.data;
            },
            function(res) {
                alert("Neuspesno dobavljanje sprinteva!");
            }
        );
    };

    //poziv za dobavljanje sprinteva
    getSprintevi();

    //dobavljanje liste pomocnog entiteta
    var getStanja = function() {
        var promise = $http.get(urlStanja);

        promise.then(
            function(res) {
                $scope.stanja = res.data;
            },
            function(res) {
                alert("Neuspesno dobavljanje stanja!");
            }
        );
    };

    //poziv za dobavljanje sprinteva
    getStanja();

    //parametri za dodavanje novog entiteta
    $scope.noviZadatak = {};
    $scope.noviZadatak.ime = "";
    $scope.noviZadatak.zaduzeni = "";
    $scope.noviZadatak.bodovi = "";
    $scope.noviZadatak.idStanja = "";
    $scope.noviZadatak.idSprinta = "";

    //funkcija za dodavanje novog entiteta
    $scope.doAdd = function() {
        var promise = $http.post(urlZadaci, $scope.noviZadatak);

        promise.then(
            function(res) {
                //brisanje vrednosti iz polja
                $scope.noviZadatak = {};
                $scope.noviZadatak.ime = "";
                $scope.noviZadatak.zaduzeni = "";
                $scope.noviZadatak.bodovi = "";
                $scope.noviZadatak.idStanja = "";
                $scope.noviZadatak.idSprinta = "";
                getZadaci();
            },
            function(res) {
                alert("Neuspesno dodavanje Zadatka!");
            }
        );
    };

    //brisanje entiteta
    $scope.doDelete = function(id) {
        var promise = $http.delete(urlZadaci + '/' + id);
        promise.then(
            function(res) {
                getZadaci();
            },
            function(res) {
                alert("Neuspesno brisanje zadatka!");
            }
        );
    };

    //pretraga, u sustini samo poziva funkciju za dobavljanje liste entiteta
    $scope.doSearch = function() {
        getZadaci();
    };

    //preusmeravanje na modul za izmenu entiteta
    $scope.goToEdit = function(id) {
        $location.path('/zadatak/edit/' + id);
    };
    //funkcija za promenu strane (paginacija), u sustini promeni vrednost parametra trenutne strane za 1 ili -1 i pozove funkciju
    //za dobavljanje liste entiteta
    $scope.changePage = function(direction) {
        $scope.pageNum += direction;
        getZadaci();
    };

    $scope.predjiNaStanje = function(id) {
        var promise = $http.put('api/zadaci/' + id + '/stanje');

        promise.then(
            function(res) {
                getZadaci();
            },
            function(res) {
                alert("Neuspesna pomena stanja!");
            }
        );
    };



});

sprintApp.controller('EditZadatakCtrl', function($scope, $http, $routeParams, $location) {

    //definisanje url za api, ovde nam treba jedan entitet pa dodajemo id
    var urlZadaci = "/api/zadaci/";
    var urlSprinteva = "/api/sprintevi";
    var urlStanja = "api/stanja";
    var urlJednogZadtka = urlZadaci + $routeParams.id;

    //lista za prihvatanje liste sa bekenda, treba nam pomocni entitet zbog izmene
    $scope.sprintevi = [];
    $scope.stanja = [];

    //dobavljanje liste pomocnog entiteta
    var getSprintevi = function() {
        var promise = $http.get(urlSprinteva);

        promise.then(
            function(res) {
                $scope.sprintevi = res.data;
                getZadatak();
            },
            function(res) {
                alert("Neuspesno dobavljanje sprinteva!");
            }
        );
    };

    //dobavljanje liste pomocnog entiteta
    var getStanja = function() {
        var promise = $http.get(urlStanja);

        promise.then(
            function(res) {
                $scope.stanja = res.data;
                //poziv za dobavljanje sprinteva
                getSprintevi();
            },
            function(res) {
                alert("Neuspesno dobavljanje stanja!");
            }
        );
    };

    //poziv za dobavljanje sprinteva
    getStanja();

    //parametri za entitet koji menjamo, inicilano dobavljamo vrednosti sa bekenda, i vezemo sa formu
    $scope.zadatak = {};
    $scope.zadatak.ime = "";
    $scope.zadatak.zaduzeni = "";
    $scope.zadatak.bodovi = "";
    $scope.zadatak.idStanja = "";
    $scope.zadatak.idSprinta = "";

    //dobavljanje jednog entiteta
    var getZadatak = function() {
        var promise = $http.get(urlJednogZadtka);
        promise.then(
            function(res) {
                $scope.zadatak = res.data;
            },
            function(res) {
                alert("Neuspesno dobavljanje zadatka!");
            }
        );
    };


    //slanje izmenjenog entiteta na bekend
    $scope.doEdit = function() {

        var promise = $http.put(urlJednogZadtka, $scope.zadatak);

        promise.then(
            function(res) {
                $location.path('/');
            },
            function(res) {
                alert("Neuspesno editovanje zadatka!");
            }
        );
    }

});


//konfiguracija za routiranje, za svaku adresu na frontendu podesimo putanju modula i naziv kontrolera
sprintApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'app/html/zadatak.html',
            controller: 'ZadaciCtrl'
        })
        .when('/zadatak/edit/:id', {
            templateUrl: 'app/html/edit-zadatak.html',
            controller: 'EditZadatakCtrl'
        })
        .otherwise({
            redirectTo: "/"
        });
}]);