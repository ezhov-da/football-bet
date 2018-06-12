//====================================================
// Выпадающий список игроков
//====================================================
Ext.define('PlayerModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'doubleUsername', type: 'string'},
        {name: 'doubleFio', type: 'string'},
    ]
});

var players = Ext.create('Ext.data.Store', {
    model: 'PlayerModel',
    proxy: {
        type: 'ajax',
        url: '/comboBoxPlayers',
        reader: {
            type: 'json',
            root: 'players'
        }
    },
    autoLoad: true
});

// ====================================================
// Выпадающий список игр
//====================================================
Ext.define('GamesModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'game', type: 'string'},
    ]
});

function reloadComboBoxGames() {
    console.log("change");
    var valuePlayer = Ext.getCmp('comboBoxPlayers').getValue();
    console.log(valuePlayer);
    var games = Ext.create('Ext.data.Store', {
        model: 'GamesModel',
        proxy: {
            type: 'ajax',
            url: '/comboBoxGames?player=' + valuePlayer,
            reader: {
                type: 'json',
                root: 'games'
            }
        },
        autoLoad: true
    });

    var comboBoxGames = Ext.getCmp('comboBoxGames');
    comboBoxGames.bindStore(games);
}


var panelInsertForecast = Ext.create('Ext.form.Panel', {
        title: 'Внести прогноз',
        region: 'north',
        id: 'panelInsertForecast',
        layout: 'hbox',
        items: [
            Ext.create('Ext.form.field.ComboBox', {
                fieldLabel: 'За кого внести',
                store: players,
                queryMode: 'local',
                valueField: 'doubleUsername',
                name: 'player',
                id: 'comboBoxPlayers',
                allowBlank: false,
                tpl: Ext.create('Ext.XTemplate',
                    '<tpl for=".">',
                    '<div class="x-boundlist-item">{doubleFio}</div>',
                    '</tpl>'
                ),
                // template for the content inside text field
                displayTpl: Ext.create('Ext.XTemplate',
                    '<tpl for=".">',
                    '{doubleFio}',
                    '</tpl>'
                ),
                listeners: {
                    select: function () {
                        reloadComboBoxGames();
                    }
                }
            }),

            Ext.create('Ext.form.field.ComboBox', {
                fieldLabel: 'На какую игру',
                queryMode: 'local',
                valueField: 'id',
                id: 'comboBoxGames',
                name: 'game',
                allowBlank: false,
                tpl: Ext.create('Ext.XTemplate',
                    '<tpl for=".">',
                    '<div class="x-boundlist-item">{game}</div>',
                    '</tpl>'
                ),
                // template for the content inside text field
                displayTpl: Ext.create('Ext.XTemplate',
                    '<tpl for=".">',
                    '{game}',
                    '</tpl>'
                )
            }),

            {
                xtype: 'label',
                forId: 'myFieldId',
                text: 'Счет: ',
                margin: '0 0 0 10'
            },
            {
                xtype: 'textfield',
                id: 'myFieldId',
                width: '30',
                name: 'one',
                allowBlank: false
            },
            {
                xtype: 'textfield',
                width: '30',
                name: 'two',
                allowBlank: false
            },
            {
                xtype: 'button',
                text: 'Внести',
                handler: function () {
                    panelInsertForecast.getForm().submit({
                        url: '/registerScore',
                        method: 'POST',
                        success: function (form, action) {
                            console.log(action);
                            var text = action.response.responseText;
                            var responseAnswer = Ext.decode(text);
                            Ext.Msg.alert(
                                'Все здорово',
                                responseAnswer.msg
                            );

                            reloadComboBoxGames();
                            resultStore.reload();
                            forecastStore.reload();

                        },
                        failure: function (form, action) {
                            console.log(action);
                            var text = action.response.responseText;
                            var responseAnswer = Ext.decode(text);
                            Ext.Msg.alert(
                                'Упс, стоп стоп стоп',
                                responseAnswer.msg
                            );
                        },
                        errors: function (form, action) {
                            console.log(action);
                            Ext.Msg.alert(
                                'Упс, стоп стоп стоп',
                                'Ошибка сервера'
                            );
                        }
                    });
                }
            }

        ]

    }
);

Ext.define('GamesResultModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'gameDate', type: 'string'},
        {name: 'game', type: 'string'},
        {name: 'gamer', type: 'string'},
        {name: 'result', type: 'string'},
        {name: 'gamerForecast', type: 'string'},
        {name: 'score', type: 'int'}
    ]
});

var resultStore = Ext.create('Ext.data.Store', {
    storeId: 'resultStore',
    autoLoad: true,
    model: 'GamesResultModel',
    proxy: {
        type: 'ajax',
        url: '/gameResult',
        reader: {
            type: 'json',
            root: 'games'
        }
    }
});

var panelGameResults = Ext.create('Ext.grid.Panel', {
        title: 'Результаты по играм',
        region: 'center',
        id: 'panelGameResults',
        store: resultStore,
        columns: [
            {text: 'Дата игры', dataIndex: 'gameDate', flex: 1},
            {text: 'Играющие команды', dataIndex: 'game', flex: 1},
            {text: 'Игрок', dataIndex: 'gamer', flex: 1},
            {text: 'Итоговый счет', dataIndex: 'result', flex: 1},
            {text: 'Прогноз игрока', dataIndex: 'gamerForecast', flex: 1},
            {text: 'Очки', dataIndex: 'score', flex: 1}
        ],
    }
);


Ext.define('ResultFinalModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'player', type: 'string'},
        {name: 'winner', type: 'string'},
        {name: 'score', type: 'string'},
        {name: 'plusFive', type: 'string'},
        {name: 'finalScore', type: 'string'},
    ]
});


var finalResultStore = Ext.create('Ext.data.Store', {
    autoLoad: true,
    model: 'ResultFinalModel',
    proxy: {
        type: 'ajax',
        url: '/finalResult',
        reader: {
            type: 'json',
            root: 'results'
        }
    }
});

var panelFinalResults = Ext.create('Ext.grid.Panel', {
    title: 'Итоговые результаты',
    id: 'panelFinalResults',
    region: 'center',
    store: finalResultStore,
    columns: [
        {text: 'Игрок', dataIndex: 'player', flex: 1},
        {text: 'Чемпион', dataIndex: 'winner', flex: 1},
        {text: 'Очки', dataIndex: 'score', flex: 1},
        {text: '+5 за чемпиона', dataIndex: 'plusFive', flex: 1},
        {text: 'Итоговые очки', dataIndex: 'finalScore', flex: 1}
    ],
});

Ext.define('ForecastModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'gameDate', type: 'string'},
        {name: 'game', type: 'string'},
        {name: 'forecast', type: 'string'},
    ]
});

var forecastStore = Ext.create('Ext.data.Store', {
    autoLoad: true,
    model: 'ForecastModel',
    proxy: {
        type: 'ajax',
        url: '/forecast',
        reader: {
            type: 'json',
            root: 'forecasts'
        }
    }
});

var panelForecast = Ext.create('Ext.grid.Panel', {
    title: 'Прогноз',
    id: 'panelForecast',
    region: 'east',
    width: '50%',
    split: true,
    store: forecastStore,
    columns: [
        {text: 'Дата игры', dataIndex: 'gameDate', flex: 1},
        {text: 'Играющие команды', dataIndex: 'game', flex: 1},
        {text: 'Прогноз игрока', dataIndex: 'forecast', flex: 1}
    ],
});


var panelGamerInformationCenter = Ext.create('Ext.panel.Panel', {
    xtype: 'panel',
    region: 'center',
    layout: 'border',
    items: [
        panelInsertForecast,
        panelGameResults,
    ]
});

var panelGamerInformationEast = Ext.create('Ext.panel.Panel', {
    xtype: 'panel',
    region: 'south',
    layout: 'border',
    height: '40%',
    split: true,
    items: [
        panelFinalResults,
        panelForecast,
    ]
});

var panelGamerInformation = Ext.create('Ext.panel.Panel', {
    title: 'Информация для участника',
    layout: 'border',
    items: [
        panelGamerInformationCenter,
        panelGamerInformationEast
    ]
});


// ====================================================
// Выпадающий список игр
//====================================================
Ext.define('AllGamesModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'game', type: 'string'},
    ]
});

var allGamesStore = Ext.create('Ext.data.Store', {
    model: 'AllGamesModel',
    proxy: {
        type: 'ajax',
        url: '/allComboBoxGames',
        reader: {
            type: 'json',
            root: 'games'
        }
    },
    autoLoad: true
});

function reloadComboBoxAllGames() {
    allGamesStore.reload();
}


var panelInsertResultGame = Ext.create('Ext.form.Panel', {
    title: 'Форма для внесения результата по игре',
    layout: 'hbox',
    items: [
        Ext.create('Ext.form.field.ComboBox', {
            fieldLabel: 'На какую игру',
            queryMode: 'local',
            store: allGamesStore,
            valueField: 'id',
            id: 'comboBoxAllGames',
            name: 'game',
            allowBlank: false,
            tpl: Ext.create('Ext.XTemplate',
                '<tpl for=".">',
                '<div class="x-boundlist-item">{game}</div>',
                '</tpl>'
            ),
            // template for the content inside text field
            displayTpl: Ext.create('Ext.XTemplate',
                '<tpl for=".">',
                '{game}',
                '</tpl>'
            )
        }),
        {
            xtype: 'label',
            forId: 'myFieldIdAll',
            text: 'Счет: ',
            margin: '0 0 0 10'
        },
        {
            xtype: 'textfield',
            id: 'myFieldIdAll',
            width: '30',
            name: 'one',
            allowBlank: false
        },
        {
            xtype: 'textfield',
            width: '30',
            name: 'two',
            allowBlank: false
        },
        {
            xtype: 'button',
            text: 'Внести',
            handler: function () {
                panelInsertResultGame.getForm().submit({
                    url: '/registerScoreGame',
                    method: 'POST',
                    success: function (form, action) {
                        console.log(action);
                        var text = action.response.responseText;
                        var responseAnswer = Ext.decode(text);
                        Ext.Msg.alert(
                            'Все здорово',
                            responseAnswer.msg
                        );

                        reloadComboBoxAllGames();

                    },
                    failure: function (form, action) {
                        console.log(action);
                        var text = action.response.responseText;
                        var responseAnswer = Ext.decode(text);
                        Ext.Msg.alert(
                            'Упс, стоп стоп стоп',
                            responseAnswer.msg
                        );
                    },
                    errors: function (form, action) {
                        console.log(action);
                        Ext.Msg.alert(
                            'Упс, стоп стоп стоп',
                            'Ошибка сервера'
                        );
                    }
                });
            }
        }
    ]

});

var basicTabPanel =
    Ext.create('Ext.tab.Panel', {
        title: 'Футбол',
        tbar: [
            {
                xtype: 'tbfill'
            },
            {
                xtype: 'label',
                html: '<a href="/logout">Выйти</a>'
            }
        ],
        items: [
            panelGamerInformation,
            panelInsertResultGame
        ]
    });


Ext.Ajax.useDefaultXhrHeader = false;

Ext.application({
    name: 'Футбол',
    launch: function () {
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: [basicTabPanel]
        });
    }
});
